import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { ApiService } from '../services/api';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-home',
  imports: [CommonModule],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home implements OnInit {
  stats = {
    livres: 0,
    revues: 0,
    enseignants: 0,
    etudiants: 0,
    particuliers: 0,
    auteurs: 0,
  };
  loading = true;
  error: string | null = null;

  constructor(
    private apiService: ApiService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.loadStats();
  }

  loadStats(): void {
    this.loading = true;
    this.error = null;

    forkJoin({
      livres: this.apiService.getLivres(),
      revues: this.apiService.getRevues(),
      enseignants: this.apiService.getEnseignants(),
      etudiants: this.apiService.getEtudiants(),
      particuliers: this.apiService.getParticuliers(),
      auteurs: this.apiService.getAuteurs(),
    }).subscribe({
      next: ({ livres, revues, enseignants, etudiants, particuliers, auteurs }) => {
        this.stats.livres = livres.length;
        this.stats.revues = revues.length;
        this.stats.enseignants = enseignants.length;
        this.stats.etudiants = etudiants.length;
        this.stats.particuliers = particuliers.length;
        this.stats.auteurs = auteurs.length;
        this.loading = false;
      },
      error: (error) => {
        this.error = 'Erreur lors du chargement des statistiques';
        console.error('Error loading stats:', error);
        this.loading = false;
      },
    });
  }

  navigateTo(route: string): void {
    this.router.navigate([`/${route}`]);
  }

  logout(): void {
    localStorage.removeItem('user');
    localStorage.removeItem('admin');
    this.router.navigate(['/connexion']);
  }
}
