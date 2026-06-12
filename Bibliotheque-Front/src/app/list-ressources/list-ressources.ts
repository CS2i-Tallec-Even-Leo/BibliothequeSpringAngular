import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { ApiService } from '../services/api';
import { Livre } from '../model/Stock/Livre';
import { Revue } from '../model/Stock/Revue';
import { Auteur } from '../model/Stock/Auteur';
import { Stockage } from '../model/Stock/Stockage';
import { Emprunt } from '../model/Emprunt';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-list-ressources',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './list-ressources.html',
  styleUrl: './list-ressources.css',
})
export class ListRessources implements OnInit {
  livres: Livre[] = [];
  revues: Revue[] = [];
  auteurs: Auteur[] = [];
  stockages: Stockage[] = [];
  retards: Emprunt[] = [];
  activeTab: 'livres' | 'revues' | 'auteurs' = 'livres';
  isAdmin = false;
  loading = false;
  error: string | null = null;
  success: string | null = null;

  newStockage: Stockage = {
    nombreTotal: 1,
    restant: 1,
    numeroTrave: 1,
    numeroEtagere: 1,
    niveau: 1,
    categorie: '',
  };

  newLivre: Livre = {
    id: 0,
    titre: '',
    caution: 0,
    codeBarre: '',
    iSBN: '',
    auteur: '',
    genre: '',
    anneePublication: undefined,
  };

  newRevue: Revue = {
    id: 0,
    titre: '',
    caution: 0,
    codeBarre: '',
    numeroVolume: undefined,
    dateParution: undefined,
  };

  selectedStockageId: number | null = null;
  notifying = false;

  constructor(
    private readonly apiService: ApiService,
    private readonly router: Router,
  ) {}

  ngOnInit(): void {
    this.isAdmin = !!localStorage.getItem('admin');
    if (!this.isAdmin) {
      this.router.navigate(['/connexion-admin']);
      return;
    }

    this.loading = true;
    this.error = null;
    forkJoin({
      livres: this.apiService.getLivres(),
      revues: this.apiService.getRevues(),
      auteurs: this.apiService.getAuteurs(),
      stockages: this.apiService.getStockages(),
      retards: this.apiService.getEmpruntsRetard(),
    }).subscribe({
      next: ({ livres, revues, auteurs, stockages, retards }) => {
        this.livres = livres;
        this.revues = revues;
        this.auteurs = auteurs;
        this.stockages = stockages;
        this.retards = retards;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Erreur lors du chargement des ressources';
        console.error('Error loading ressources:', err);
        this.loading = false;
      },
    });
  }

  createStockage(): void {
    this.error = null;
    this.success = null;

    if (!this.newStockage.categorie) {
      this.error = 'La categorie du stockage est obligatoire.';
      return;
    }

    this.apiService.createStockage(this.newStockage).subscribe({
      next: (created) => {
        this.stockages = [...this.stockages, created];
        this.selectedStockageId = created.id ?? null;
        this.success = 'Emplacement cree avec succes.';
      },
      error: (err) => {
        console.error('Error creating stockage:', err);
        this.error = "Erreur lors de la creation de l'emplacement";
      },
    });
  }

  createLivre(): void {
    this.error = null;
    this.success = null;
    if (!this.selectedStockageId) {
      this.error = 'Selectionnez d abord un emplacement.';
      return;
    }

    const payload: Livre = {
      ...this.newLivre,
      stockage: { id: this.selectedStockageId } as Stockage,
    };

    this.apiService.createLivre(payload).subscribe({
      next: (created) => {
        this.livres = [...this.livres, created];
        this.success = 'Livre cree avec emplacement associe.';
      },
      error: (err) => {
        console.error('Error creating livre:', err);
        this.error = 'Erreur lors de la creation du livre';
      },
    });
  }

  createRevue(): void {
    this.error = null;
    this.success = null;
    if (!this.selectedStockageId) {
      this.error = 'Selectionnez d abord un emplacement.';
      return;
    }

    const payload: Revue = {
      ...this.newRevue,
      stockage: { id: this.selectedStockageId } as Stockage,
    };

    this.apiService.createRevue(payload).subscribe({
      next: (created) => {
        this.revues = [...this.revues, created];
        this.success = 'Revue creee avec emplacement associe.';
      },
      error: (err) => {
        console.error('Error creating revue:', err);
        this.error = 'Erreur lors de la creation de la revue';
      },
    });
  }

  notifierRetards(): void {
    this.notifying = true;
    this.error = null;
    this.success = null;

    this.apiService.notifierRetards().subscribe({
      next: (result) => {
        this.success = `Notifications envoyees: ${result.notificationsEnvoyees}/${result.retards} (${result.mode})`;
      },
      error: (err) => {
        console.error('Error notifying retards:', err);
        this.error = 'Erreur lors de la notification des retards';
      },
      complete: () => {
        this.notifying = false;
      },
    });
  }

  formatRetard(emprunt: Emprunt): string {
    const user = emprunt.particulier
      ? `${emprunt.particulier.nom} ${emprunt.particulier.prenom}`
      : 'Utilisateur';
    const titre = emprunt.exemplaire?.ressource?.titre ?? 'Ressource';
    return `${user} - ${titre} (retour prevu ${emprunt.dateRetourPrevue})`;
  }

  switchTab(tab: 'livres' | 'revues' | 'auteurs'): void {
    this.activeTab = tab;
  }

  deleteLivre(id: number): void {
    if (confirm('Etes-vous sur?')) {
      this.apiService.deleteLivre(id).subscribe({
        next: () => {
          this.livres = this.livres.filter((l) => l.id !== id);
        },
        error: (err) => {
          console.error('Error deleting livre:', err);
          this.error = 'Erreur lors de la suppression';
        },
      });
    }
  }

  deleteRevue(id: number): void {
    if (confirm('Etes-vous sur?')) {
      this.apiService.deleteRevue(id).subscribe({
        next: () => {
          this.revues = this.revues.filter((r) => r.id !== id);
        },
        error: (err) => {
          console.error('Error deleting revue:', err);
          this.error = 'Erreur lors de la suppression';
        },
      });
    }
  }

  deleteAuteur(id: number): void {
    if (confirm('Etes-vous sur?')) {
      this.apiService.deleteAuteur(id).subscribe({
        next: () => {
          this.auteurs = this.auteurs.filter((a) => a.id !== id);
        },
        error: (err) => {
          console.error('Error deleting auteur:', err);
          this.error = 'Erreur lors de la suppression';
        },
      });
    }
  }
}
