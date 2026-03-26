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
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './create-user.html',
  styleUrl: './create-user.css',
})
export class CreateUser implements OnInit {
  userType: 'enseignant' | 'etudiant' | 'particulier' = 'etudiant';
  
  enseignant: Enseignant = {
    id: 0,
    nom: '',
    prenom: '',
    email: '',
    telephone: '',
    adresse: '',
    dateInscription: new Date(),
    departement: undefined,
    specialite: '',
  };

  etudiant: Etudiant = {
    id: 0,
    nom: '',
    prenom: '',
    email: '',
    telephone: '',
    adresse: '',
    dateInscription: new Date(),
    departement: undefined,
    numeroEtudiant: '',
  };

  particulier: Particulier = {
    id: 0,
    nom: '',
    prenom: '',
    email: '',
    telephone: '',
    adresse: '',
    dateInscription: new Date(),
    ville: undefined,
  };

  departements: Departement[] = [];
  villes: Ville[] = [];
  loading = false;
  submitted = false;
  error: string | null = null;
  success: string | null = null;

  constructor(private apiService: ApiService, private router: Router) {}

  ngOnInit(): void {
    this.loadDepartements();
    this.loadVilles();
  }

  loadDepartements(): void {
    this.apiService.getDepartements().subscribe(
      (data) => {
        this.departements = data;
      },
      (error) => {
        console.error('Error loading departements:', error);
      }
    );
  }

  loadVilles(): void {
    this.apiService.getVilles().subscribe(
      (data) => {
        this.villes = data;
      },
      (error) => {
        console.error('Error loading villes:', error);
      }
    );
  }

  onSubmit(): void {
    this.submitted = true;
    this.error = null;
    this.success = null;
    this.loading = true;

    if (this.userType === 'enseignant') {
      this.apiService.createEnseignant(this.enseignant).subscribe(
        (response) => {
          this.success = 'Enseignant créé avec succès!';
          this.loading = false;
          setTimeout(() => {
            this.router.navigate(['/list-users']);
          }, 1500);
        },
        (error) => {
          this.error = 'Erreur lors de la création de l\'enseignant';
          console.error('Error creating enseignant:', error);
          this.loading = false;
        }
      );
    } else if (this.userType === 'etudiant') {
      this.apiService.createEtudiant(this.etudiant).subscribe(
        (response) => {
          this.success = 'Étudiant créé avec succès!';
          this.loading = false;
          setTimeout(() => {
            this.router.navigate(['/list-users']);
          }, 1500);
        },
        (error) => {
          this.error = 'Erreur lors de la création de l\'étudiant';
          console.error('Error creating etudiant:', error);
          this.loading = false;
        }
      );
    } else if (this.userType === 'particulier') {
      this.apiService.createParticulier(this.particulier).subscribe(
        (response) => {
          this.success = 'Particulier créé avec succès!';
          this.loading = false;
          setTimeout(() => {
            this.router.navigate(['/list-users']);
          }, 1500);
        },
        (error) => {
          this.error = 'Erreur lors de la création du particulier';
          console.error('Error creating particulier:', error);
          this.loading = false;
        }
      );
    }
  }

  switchUserType(type: 'enseignant' | 'etudiant' | 'particulier'): void {
    this.userType = type;
    this.submitted = false;
  }
}
