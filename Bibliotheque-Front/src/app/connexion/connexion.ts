import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { Router } from '@angular/router';
import { ApiService } from '../services/api';

@Component({
  selector: 'app-connexion',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './connexion.html',
  styleUrl: './connexion.css',
})
export class Connexion {
  nom: string = '';
  prenom: string = '';
  loading = false;
  error: string | null = null;

  constructor(
    private router: Router,
    private apiService: ApiService,
  ) {}

  onSubmit(): void {
    this.loading = true;
    this.error = null;

    if (!this.nom || !this.prenom) {
      this.error = 'Veuillez remplir tous les champs';
      this.loading = false;
      return;
    }

    this.apiService.loginUtilisateur({ nom: this.nom, prenom: this.prenom }).subscribe({
      next: (user) => {
        localStorage.setItem('user', JSON.stringify(user));
        this.router.navigate(['/emprunts']);
      },
      error: () => {
        this.error = 'Utilisateur introuvable. Verifiez nom et prenom.';
        this.loading = false;
      },
      complete: () => {
        this.loading = false;
      },
    });
  }
}
