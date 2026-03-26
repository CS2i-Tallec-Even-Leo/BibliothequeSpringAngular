import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-connexion',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './connexion.html',
  styleUrl: './connexion.css',
})
export class Connexion {
  email: string = '';
  password: string = '';
  loading = false;
  error: string | null = null;

  constructor(private router: Router) {}

  onSubmit(): void {
    this.loading = true;
    this.error = null;

    // Mock authentication - replace with actual API call
    if (this.email && this.password) {
      // Store token/user info in localStorage
      localStorage.setItem('user', JSON.stringify({ email: this.email }));
      this.router.navigate(['/home']);
    } else {
      this.error = 'Veuillez remplir tous les champs';
      this.loading = false;
    }
  }
}
