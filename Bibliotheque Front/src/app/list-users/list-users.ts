import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { ApiService } from '../services/api';
import { Enseignant } from '../model/User/Enseignant';
import { Etudiant } from '../model/User/Etudiant';
import { Particulier } from '../model/User/Particulier';

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
    this.loadEnseignants();
  }

  loadEnseignants(): void {
    this.loading = true;
    this.error = null;
    this.apiService.getEnseignants().subscribe(
      (data) => {
        this.enseignants = data;
        this.loading = false;
      },
      (error) => {
        this.error = 'Erreur lors du chargement des enseignants';
        console.error('Error loading enseignants:', error);
        this.loading = false;
      }
    );
  }

  loadEtudiants(): void {
    this.loading = true;
    this.error = null;
    this.apiService.getEtudiants().subscribe(
      (data) => {
        this.etudiants = data;
        this.loading = false;
      },
      (error) => {
        this.error = 'Erreur lors du chargement des étudiants';
        console.error('Error loading etudiants:', error);
        this.loading = false;
      }
    );
  }

  loadParticuliers(): void {
    this.loading = true;
    this.error = null;
    this.apiService.getParticuliers().subscribe(
      (data) => {
        this.particuliers = data;
        this.loading = false;
      },
      (error) => {
        this.error = 'Erreur lors du chargement des particuliers';
        console.error('Error loading particuliers:', error);
        this.loading = false;
      }
    );
  }

  switchTab(tab: 'enseignants' | 'etudiants' | 'particuliers'): void {
    this.activeTab = tab;
    if (tab === 'enseignants' && this.enseignants.length === 0) {
      this.loadEnseignants();
    } else if (tab === 'etudiants' && this.etudiants.length === 0) {
      this.loadEtudiants();
    } else if (tab === 'particuliers' && this.particuliers.length === 0) {
      this.loadParticuliers();
    }
  }

  deleteTeacher(id: number): void {
    if (confirm('Êtes-vous sûr?')) {
      this.apiService.deleteEnseignant(id).subscribe(
        () => {
          this.enseignants = this.enseignants.filter((e) => e.id !== id);
        },
        (error) => {
          console.error('Error deleting enseignant:', error);
          this.error = 'Erreur lors de la suppression';
        }
      );
    }
  }

  deleteStudent(id: number): void {
    if (confirm('Êtes-vous sûr?')) {
      this.apiService.deleteEtudiant(id).subscribe(
        () => {
          this.etudiants = this.etudiants.filter((e) => e.id !== id);
        },
        (error) => {
          console.error('Error deleting etudiant:', error);
          this.error = 'Erreur lors de la suppression';
        }
      );
    }
  }

  deleteParticulier(id: number): void {
    if (confirm('Êtes-vous sûr?')) {
      this.apiService.deleteParticulier(id).subscribe(
        () => {
          this.particuliers = this.particuliers.filter((p) => p.id !== id);
        },
        (error) => {
          console.error('Error deleting particulier:', error);
          this.error = 'Erreur lors de la suppression';
        }
      );
    }
  }
}
