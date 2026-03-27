import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { ApiService } from '../services/api';
import { Enseignant } from '../model/User/Enseignant';
import { Etudiant } from '../model/User/Etudiant';
import { Particulier } from '../model/User/Particulier';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-list-users',
  imports: [CommonModule, RouterLink],
  templateUrl: './list-users.html',
  styleUrl: './list-users.css',
})
export class ListUsers implements OnInit {
  enseignants: Enseignant[] = [];
  etudiants: Etudiant[] = [];
  particuliers: Particulier[] = [];
  activeTab: 'enseignants' | 'etudiants' | 'particuliers' = 'enseignants';
  loading = false;
  error: string | null = null;

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.loading = true;
    this.error = null;
    forkJoin({
      enseignants: this.apiService.getEnseignants(),
      etudiants: this.apiService.getEtudiants(),
      particuliers: this.apiService.getParticuliers(),
    }).subscribe({
      next: ({ enseignants, etudiants, particuliers }) => {
        this.enseignants = enseignants;
        this.etudiants = etudiants;
        this.particuliers = particuliers;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Erreur lors du chargement des utilisateurs';
        console.error('Error loading users:', err);
        this.loading = false;
      },
    });
  }

  switchTab(tab: 'enseignants' | 'etudiants' | 'particuliers'): void {
    this.activeTab = tab;
  }

  deleteTeacher(id: number): void {
    if (confirm('Etes-vous sur?')) {
      this.apiService.deleteEnseignant(id).subscribe({
        next: () => {
          this.enseignants = this.enseignants.filter((e) => e.id !== id);
        },
        error: (err) => {
          console.error('Error deleting enseignant:', err);
          this.error = 'Erreur lors de la suppression';
        },
      });
    }
  }

  deleteStudent(id: number): void {
    if (confirm('Etes-vous sur?')) {
      this.apiService.deleteEtudiant(id).subscribe({
        next: () => {
          this.etudiants = this.etudiants.filter((e) => e.id !== id);
        },
        error: (err) => {
          console.error('Error deleting etudiant:', err);
          this.error = 'Erreur lors de la suppression';
        },
      });
    }
  }

  deleteParticulier(id: number): void {
    if (confirm('Etes-vous sur?')) {
      this.apiService.deleteParticulier(id).subscribe({
        next: () => {
          this.particuliers = this.particuliers.filter((p) => p.id !== id);
        },
        error: (err) => {
          console.error('Error deleting particulier:', err);
          this.error = 'Erreur lors de la suppression';
        },
      });
    }
  }
}
