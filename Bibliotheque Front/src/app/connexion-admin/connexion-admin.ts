import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

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

  constructor(private router: Router) {}

  onSubmit(): void {
    this.loading = true;
    this.error = null;

    // Mock authentication - replace with actual API call
    if (this.email && this.password) {
      // Store admin token/user info in localStorage
      localStorage.setItem('admin', JSON.stringify({ email: this.email, role: 'ADMIN' }));
      this.router.navigate(['/home']);
    } else {
      this.error = 'Veuillez remplir tous les champs';
      this.loading = false;
    }
  }
}
