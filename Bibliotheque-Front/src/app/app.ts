import { Component, signal, OnInit } from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';
import { SeedDataService } from './services/seed-data.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App implements OnInit {
  protected readonly title = signal('Bibliotheque');

  constructor(private readonly seedDataService: SeedDataService) {}

  ngOnInit(): void {
    this.seedDataService.initialize();
  }
}
