import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { forkJoin } from 'rxjs';
import { ApiService } from '../services/api';
import { Exemplaire } from '../model/Stock/Exemplaire';
import { Emprunt } from '../model/Emprunt';
import { Particulier } from '../model/User/Particulier';

interface ConnectedUser {
  id: number;
  nom: string;
  prenom: string;
  type: string;
}

@Component({
  selector: 'app-emprunt',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './emprunt.html',
  styleUrl: './emprunt.css',
})
export class EmpruntPage implements OnInit {
  connectedUser: ConnectedUser | null = null;
  exemplaires: Exemplaire[] = [];
  emprunts: Emprunt[] = [];

  search = {
    nomOuvrage: '',
    auteur: '',
    anneePublication: null as number | null,
    theme: '',
  };

  loading = false;
  submitting = false;
  error: string | null = null;
  success: string | null = null;

  form = {
    exemplaireId: null as number | null,
    dateEmprunt: this.todayAsInputDate(),
  };

  constructor(
    private apiService: ApiService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    const userRaw = localStorage.getItem('user');
    if (!userRaw) {
      this.router.navigate(['/connexion']);
      return;
    }

    try {
      const parsed = JSON.parse(userRaw) as Partial<ConnectedUser>;
      const userId = Number(parsed.id);
      if (!Number.isFinite(userId) || userId <= 0) {
        throw new Error('Invalid user id');
      }

      this.connectedUser = {
        id: userId,
        nom: parsed.nom ?? '',
        prenom: parsed.prenom ?? '',
        type: parsed.type ?? 'PARTICULIER',
      };
    } catch {
      localStorage.removeItem('user');
      this.router.navigate(['/connexion']);
      return;
    }

    this.loadData();
  }

  loadData(): void {
    if (!this.connectedUser) {
      return;
    }

    this.loading = true;
    this.error = null;

    forkJoin({
      exemplaires: this.apiService.getExemplaires(),
      emprunts: this.apiService.getEmpruntsByUtilisateur(this.connectedUser.id),
    }).subscribe({
      next: ({ exemplaires, emprunts }) => {
        this.exemplaires = exemplaires;
        this.emprunts = emprunts;
        this.loading = false;
      },
      error: (err) => {
        this.error = "Erreur lors du chargement des donnees d'emprunt";
        console.error('Error loading emprunt data:', err);
        this.loading = false;
      },
    });
  }

  onSubmit(): void {
    this.success = null;
    this.error = null;

    if (!this.connectedUser) {
      this.error = 'Vous devez etre connecte pour emprunter.';
      return;
    }

    if (!this.form.exemplaireId || !this.form.dateEmprunt) {
      this.error = 'Veuillez remplir les champs obligatoires.';
      return;
    }

    const exemplaire = this.filteredExemplaires.find((e) => e.id === this.form.exemplaireId);

    if (!exemplaire) {
      this.error = 'Exemplaire invalide.';
      return;
    }

    if (!exemplaire.id) {
      this.error = "L'exemplaire selectionne est invalide.";
      return;
    }

    if (this.isExemplaireBorrowed(exemplaire.id)) {
      this.error = 'Cet exemplaire est deja emprunte (statut EN_COURS).';
      return;
    }

    const payload: Emprunt = {
      dateEmprunt: this.form.dateEmprunt,
      statut: 'EN_COURS',
      particulier: { id: this.connectedUser.id } as Particulier,
      exemplaire: { id: exemplaire.id } as Exemplaire,
    };

    this.submitting = true;
    this.apiService.createEmprunt(payload).subscribe({
      next: (created) => {
        this.emprunts = [created, ...this.emprunts];
        this.success = 'Emprunt enregistre avec succes.';
        this.submitting = false;
        this.form.exemplaireId = null;
      },
      error: (err: HttpErrorResponse) => {
        const backendMessage =
          (typeof err.error === 'object' ? err.error?.message : null) ||
          (typeof err.error === 'string' ? err.error : null);
        this.error =
          backendMessage ?? `Erreur lors de la creation de l'emprunt (HTTP ${err.status}).`;
        console.error('Error creating emprunt:', err);
        this.submitting = false;
      },
    });
  }

  isExemplaireBorrowed(exemplaireId?: number): boolean {
    if (!exemplaireId) {
      return false;
    }

    return this.emprunts.some(
      (emprunt) => emprunt.exemplaire?.id === exemplaireId && emprunt.statut === 'EN_COURS',
    );
  }

  formatParticulier(particulier?: Particulier): string {
    if (!particulier) {
      return 'Utilisateur inconnu';
    }

    return `${particulier.nom} ${particulier.prenom}`.trim();
  }

  formatExemplaire(exemplaire?: Exemplaire): string {
    if (!exemplaire) {
      return 'Exemplaire inconnu';
    }

    const titre = exemplaire.ressource?.titre ?? 'Ressource sans titre';
    return `${titre} (#${exemplaire.numeroExemplaire})`;
  }

  get filteredExemplaires(): Exemplaire[] {
    return this.exemplaires.filter((exemplaire) => {
      if (this.isExemplaireBorrowed(exemplaire.id)) {
        return false;
      }

      const resource = exemplaire.ressource;
      if (!resource) {
        return false;
      }

      const titre = resource.titre?.toLowerCase() ?? '';
      const nomFilter = this.search.nomOuvrage.trim().toLowerCase();
      const byNom = !nomFilter || titre.includes(nomFilter);

      const hasDetailedFilter =
        !!this.search.auteur.trim() || !!this.search.theme.trim() || !!this.search.anneePublication;

      if (!hasDetailedFilter) {
        return byNom;
      }

      const isBook = 'auteur' in resource || 'genre' in resource || 'anneePublication' in resource;
      if (!isBook) {
        return false;
      }

      const livre = resource as {
        auteur?: string;
        genre?: string;
        anneePublication?: number;
      };

      const auteurFilter = this.search.auteur.trim().toLowerCase();
      const themeFilter = this.search.theme.trim().toLowerCase();
      const anneeFilter = this.search.anneePublication;

      const byAuteur = !auteurFilter || (livre.auteur ?? '').toLowerCase().includes(auteurFilter);
      const byTheme = !themeFilter || (livre.genre ?? '').toLowerCase().includes(themeFilter);
      const byAnnee = !anneeFilter || livre.anneePublication === anneeFilter;

      return byNom && byAuteur && byTheme && byAnnee;
    });
  }

  clearSearch(): void {
    this.search.nomOuvrage = '';
    this.search.auteur = '';
    this.search.anneePublication = null;
    this.search.theme = '';
  }

  private todayAsInputDate(): string {
    const now = new Date();
    const month = `${now.getMonth() + 1}`.padStart(2, '0');
    const day = `${now.getDate()}`.padStart(2, '0');
    return `${now.getFullYear()}-${month}-${day}`;
  }
}
