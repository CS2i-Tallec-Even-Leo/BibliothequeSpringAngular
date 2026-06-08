import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../services/api';
import { Enseignant } from '../model/User/Enseignant';
import { Etudiant } from '../model/User/Etudiant';
import { Particulier } from '../model/User/Particulier';
import { Departement } from '../model/User/Departement';
import { Ville } from '../model/User/Ville';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-create-user',
  standalone: true, // ✅ important si Angular moderne
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './create-user.html',
  styleUrl: './create-user.css',
})
export class CreateUser implements OnInit {
  userType: 'enseignant' | 'etudiant' | 'particulier' = 'etudiant';
  readonly userTypes: Array<'enseignant' | 'etudiant' | 'particulier'> = [
    'etudiant',
    'enseignant',
    'particulier',
  ];

  enseignant: Enseignant = {
    id: 0,
    nom: '',
    prenom: '',
    adresse: '',
    codeVille: '',
    email: '',
    caution: 0,
    codeDepartement: null,
  };

  etudiant: Etudiant = {
    id: 0,
    nom: '',
    prenom: '',
    adresse: '',
    codeVille: '',
    email: '',
    caution: 0,
    anneeUniversitaire: null,
  };

  particulier: Particulier = {
    id: 0,
    nom: '',
    prenom: '',
    adresse: '',
    codeVille: '',
    email: '',
    caution: 0,
  };

  currentUser: Enseignant | Etudiant | Particulier = this.etudiant;

  departements: Departement[] = [];
  villes: Ville[] = [];

  loading = false;
  submitted = false;
  error: string | null = null;
  success: string | null = null;

  constructor(
    private apiService: ApiService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    if (!localStorage.getItem('admin')) {
      this.router.navigate(['/connexion-admin']);
      return;
    }

    this.switchUserType(this.userType);
    this.loadDepartements();
    this.loadVilles();
  }

  // -----------------------
  // LOAD DATA
  // -----------------------
  loadDepartements(): void {
    this.apiService.getDepartements().subscribe({
      next: (data) => {
        this.departements = data;
      },
      error: (error) => {
        console.error('Error loading departements:', error);
      },
    });
  }

  loadVilles(): void {
    this.apiService.getVilles().subscribe({
      next: (data) => {
        this.villes = data;
      },
      error: (error) => {
        console.error('Error loading villes:', error);
      },
    });
  }

  // -----------------------
  // SWITCH USER TYPE
  // -----------------------
  switchUserType(type: 'enseignant' | 'etudiant' | 'particulier') {
    this.userType = type;

    if (type === 'etudiant') {
      this.currentUser = this.etudiant;
    } else if (type === 'enseignant') {
      this.currentUser = this.enseignant;
    } else {
      this.currentUser = this.particulier;
    }
  }

  // -----------------------
  // SUBMIT
  // -----------------------
  onSubmit(): void {
    this.submitted = true;
    this.error = null;
    this.success = null;

    if (!this.currentUser.nom || !this.currentUser.prenom || !this.currentUser.email) {
      this.error = 'Nom, prenom et email sont obligatoires.';
      return;
    }

    this.loading = true;

    let request$;

    if (this.userType === 'enseignant') {
      request$ = this.apiService.createEnseignant(this.enseignant);
    } else if (this.userType === 'etudiant') {
      request$ = this.apiService.createEtudiant(this.etudiant);
    } else {
      request$ = this.apiService.createParticulier(this.particulier);
    }

    request$.subscribe({
      next: () => {
        this.success = 'Utilisateur créé avec succès !';
        this.loading = false;

        setTimeout(() => {
          this.router.navigate(['/list-users']);
        }, 1500);
      },
      error: (error) => {
        this.error = 'Erreur lors de la création';
        console.error('Error:', error);
        this.loading = false;
      },
    });
  }
}
