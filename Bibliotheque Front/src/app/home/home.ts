import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { ApiService } from '../services/api';

@Component({
  selector: 'app-home',
  imports: [CommonModule, RouterLink],
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

  constructor(private apiService: ApiService, private router: Router) {}

  ngOnInit(): void {
    this.loadStats();
  }

  loadStats(): void {
    this.loading = true;
    this.error = null;

    // Load all stats in parallel
    Promise.all([
      this.apiService.getLivres().toPromise(),
      this.apiService.getRevues().toPromise(),
      this.apiService.getEnseignants().toPromise(),
      this.apiService.getEtudiants().toPromise(),
      this.apiService.getParticuliers().toPromise(),
      this.apiService.getAuteurs().toPromise(),
    ])
      .then((results: any) => {
        this.stats.livres = results[0]?.length || 0;
        this.stats.revues = results[1]?.length || 0;
        this.stats.enseignants = results[2]?.length || 0;
        this.stats.etudiants = results[3]?.length || 0;
        this.stats.particuliers = results[4]?.length || 0;
        this.stats.auteurs = results[5]?.length || 0;
        this.loading = false;
      })
      .catch((error) => {
        this.error = 'Erreur lors du chargement des statistiques';
        console.error('Error loading stats:', error);
        this.loading = false;
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
