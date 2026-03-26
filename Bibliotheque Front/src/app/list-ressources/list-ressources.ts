import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from '../services/api';
import { Livre } from '../model/Stock/Livre';
import { Revue } from '../model/Stock/Revue';
import { Auteur } from '../model/Stock/Auteur';

@Component({
  selector: 'app-list-ressources',
  imports: [CommonModule],
  templateUrl: './list-ressources.html',
  styleUrl: './list-ressources.css',
})
export class ListRessources implements OnInit {
  livres: Livre[] = [];
  revues: Revue[] = [];
  auteurs: Auteur[] = [];
  activeTab: 'livres' | 'revues' | 'auteurs' = 'livres';
  loading = false;
  error: string | null = null;

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.loadLivres();
  }

  loadLivres(): void {
    this.loading = true;
    this.error = null;
    this.apiService.getLivres().subscribe(
      (data) => {
        this.livres = data;
        this.loading = false;
      },
      (error) => {
        this.error = 'Erreur lors du chargement des livres';
        console.error('Error loading livres:', error);
        this.loading = false;
      }
    );
  }

  loadRevues(): void {
    this.loading = true;
    this.error = null;
    this.apiService.getRevues().subscribe(
      (data) => {
        this.revues = data;
        this.loading = false;
      },
      (error) => {
        this.error = 'Erreur lors du chargement des revues';
        console.error('Error loading revues:', error);
        this.loading = false;
      }
    );
  }

  loadAuteurs(): void {
    this.loading = true;
    this.error = null;
    this.apiService.getAuteurs().subscribe(
      (data) => {
        this.auteurs = data;
        this.loading = false;
      },
      (error) => {
        this.error = 'Erreur lors du chargement des auteurs';
        console.error('Error loading auteurs:', error);
        this.loading = false;
      }
    );
  }

  switchTab(tab: 'livres' | 'revues' | 'auteurs'): void {
    this.activeTab = tab;
    if (tab === 'livres' && this.livres.length === 0) {
      this.loadLivres();
    } else if (tab === 'revues' && this.revues.length === 0) {
      this.loadRevues();
    } else if (tab === 'auteurs' && this.auteurs.length === 0) {
      this.loadAuteurs();
    }
  }

  deleteLivre(id: number): void {
    if (confirm('Êtes-vous sûr?')) {
      this.apiService.deleteLivre(id).subscribe(
        () => {
          this.livres = this.livres.filter((l) => l.id !== id);
        },
        (error) => {
          console.error('Error deleting livre:', error);
          this.error = 'Erreur lors de la suppression';
        }
      );
    }
  }

  deleteRevue(id: number): void {
    if (confirm('Êtes-vous sûr?')) {
      this.apiService.deleteRevue(id).subscribe(
        () => {
          this.revues = this.revues.filter((r) => r.id !== id);
        },
        (error) => {
          console.error('Error deleting revue:', error);
          this.error = 'Erreur lors de la suppression';
        }
      );
    }
  }

  deleteAuteur(id: number): void {
    if (confirm('Êtes-vous sûr?')) {
      this.apiService.deleteAuteur(id).subscribe(
        () => {
          this.auteurs = this.auteurs.filter((a) => a.id !== id);
        },
        (error) => {
          console.error('Error deleting auteur:', error);
          this.error = 'Erreur lors de la suppression';
        }
      );
    }
  }
}
