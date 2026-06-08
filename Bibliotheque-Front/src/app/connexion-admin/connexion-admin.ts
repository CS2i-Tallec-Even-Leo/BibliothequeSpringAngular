import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { ApiService } from '../services/api';

@Component({
  selector: 'app-connexion-admin',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './connexion-admin.html',
  styleUrl: './connexion-admin.css',
})
export class ConnexionAdmin {
  email: string = '';
  password: string = '';
  loading = false;
  error: string | null = null;

  constructor(
    private router: Router,
    private apiService: ApiService,
  ) {}

  onSubmit(): void {
    this.loading = true;
    this.error = null;

    if (!this.email || !this.password) {
      this.error = 'Veuillez remplir tous les champs';
      this.loading = false;
      return;
    }

    this.apiService.loginAdmin({ email: this.email, password: this.password }).subscribe({
      next: (admin) => {
        localStorage.setItem('admin', JSON.stringify(admin));
        this.router.navigate(['/list-ressources']);
      },
      error: () => {
        this.error = 'Identifiants bibliothecaire invalides';
        this.loading = false;
      },
      complete: () => {
        this.loading = false;
      },
    });
  }
}
