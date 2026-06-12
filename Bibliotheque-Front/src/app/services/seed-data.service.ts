import { Injectable } from '@angular/core';
import { ApiService } from './api';
import { catchError, switchMap, tap } from 'rxjs/operators';
import { forkJoin, of, EMPTY } from 'rxjs';

type HttpErrorLike = {
  status?: number;
  message?: string;
  error?: unknown;
};

type SeedInitResult = {
  d1: unknown;
  d2: unknown;
  v1: unknown;
  v2: unknown;
};

type SeedInsertResult = (unknown | null)[];

function logError(label: string) {
  return catchError((err: unknown) => {
    const error = err as HttpErrorLike;
    console.error(
      `[SEED] Erreur creation ${label}:`,
      error?.status,
      error?.message,
      error?.error
    );
    return of(null);
  });
}

@Injectable({ providedIn: 'root' })
export class SeedDataService {
  constructor(private readonly apiService: ApiService) {}

  initialize(): void {
    this.apiService
      .getLivres()
      .pipe(
        catchError(() => {
          console.warn('Backend inaccessible, seed annule.');
          return EMPTY;
        }),

        switchMap((livres: unknown[]) => {
  if (livres.length > 0) {
    console.log('Donnees deja presentes, seed ignore.');
    return EMPTY;
  }

  console.log('[SEED] Base vide, insertion des donnees...');

  return forkJoin({
    d1: this.apiService
      .createDepartement({ codeDepartement: 1, nomDepartement: 'Informatique' })
      .pipe(logError('Departement Informatique')),
    d2: this.apiService
      .createDepartement({ codeDepartement: 2, nomDepartement: 'Lettres' })
      .pipe(logError('Departement Lettres')),
    v1: this.apiService
      .createVille({ codeVille: 75001, nomVille: 'Paris' })
      .pipe(logError('Ville Paris')),
    v2: this.apiService
      .createVille({ codeVille: 69001, nomVille: 'Lyon' })
      .pipe(logError('Ville Lyon')),
  });
}),

tap(() =>
  console.log('[SEED] Departements/Villes OK, insertion auteurs/livres...')
),
        switchMap((_result: SeedInitResult) =>
          forkJoin<SeedInsertResult>([
            this.apiService
              .createAuteur({ id: 0, nom: 'Hugo', prenom: 'Victor' })
              .pipe(logError('Auteur Hugo')),
            this.apiService
              .createAuteur({ id: 0, nom: 'Camus', prenom: 'Albert' })
              .pipe(logError('Auteur Camus')),
            this.apiService
              .createAuteur({ id: 0, nom: 'Zola', prenom: 'Emile' })
              .pipe(logError('Auteur Zola')),
            this.apiService
              .createLivre({ id: 0, titre: 'Les Miserables', caution: 15, codeBarre: '000123456001', iSBN: '978-2253005049', auteur: 'Victor Hugo', genre: 'Roman' })
              .pipe(logError('Livre Les Miserables')),
            this.apiService
              .createLivre({ id: 0, titre: 'Notre-Dame de Paris', caution: 13, codeBarre: '000123456002', iSBN: '978-2253009634', auteur: 'Victor Hugo', genre: 'Roman historique' })
              .pipe(logError('Livre Notre-Dame de Paris')),
            this.apiService
              .createLivre({ id: 0, titre: 'L Etranger', caution: 10, codeBarre: '000123456003', iSBN: '978-2070360024', auteur: 'Albert Camus', genre: 'Roman' })
              .pipe(logError('Livre L Etranger')),
            this.apiService
              .createLivre({ id: 0, titre: 'La Peste', caution: 10, codeBarre: '000123456004', iSBN: '978-2070360315', auteur: 'Albert Camus', genre: 'Roman' })
              .pipe(logError('Livre La Peste')),
            this.apiService
              .createLivre({ id: 0, titre: 'Germinal', caution: 12, codeBarre: '000123456005', iSBN: '978-2253006077', auteur: 'Emile Zola', genre: 'Roman' })
              .pipe(logError('Livre Germinal')),
            this.apiService
              .createLivre({ id: 0, titre: 'Nana', caution: 11, codeBarre: '000123456006', iSBN: '978-2253004219', auteur: 'Emile Zola', genre: 'Roman' })
              .pipe(logError('Livre Nana')),
            this.apiService
              .createLivre({ id: 0, titre: 'Madame Bovary', caution: 10, codeBarre: '000123456007', iSBN: '978-2070413119', auteur: 'Gustave Flaubert', genre: 'Roman' })
              .pipe(logError('Livre Madame Bovary')),
            this.apiService
              .createLivre({ id: 0, titre: 'Le Pere Goriot', caution: 11, codeBarre: '000123456008', iSBN: '978-2253004257', auteur: 'Honore de Balzac', genre: 'Roman' })
              .pipe(logError('Livre Le Pere Goriot')),
            this.apiService
              .createLivre({ id: 0, titre: 'Candide', caution: 8, codeBarre: '000123456009', iSBN: '978-2070360352', auteur: 'Voltaire', genre: 'Conte philosophique' })
              .pipe(logError('Livre Candide')),
            this.apiService
              .createLivre({ id: 0, titre: 'Les Fleurs du Mal', caution: 9, codeBarre: '000123456010', iSBN: '978-2070411579', auteur: 'Charles Baudelaire', genre: 'Poesie' })
              .pipe(logError('Livre Les Fleurs du Mal')),
            this.apiService
              .createRevue({ id: 0, titre: 'Sciences et Vie', caution: 5, codeBarre: '000234567001', numeroVolume: 1320 })
              .pipe(logError('Revue Sciences et Vie')),
            this.apiService
              .createRevue({ id: 0, titre: 'Le Monde Diplomatique', caution: 4, codeBarre: '000234567002', numeroVolume: 856 })
              .pipe(logError('Revue Le Monde Diplomatique')),
            this.apiService
              .createRevue({ id: 0, titre: 'Pour la Science', caution: 6, codeBarre: '000234567003', numeroVolume: 580 })
              .pipe(logError('Revue Pour la Science')),
            this.apiService
              .createEnseignant({ id: 0, nom: 'Dupont', prenom: 'Marie', adresse: '10 rue de Rome, Paris', codeVille: '75001', caution: 0, codeDepartement: 1 })
              .pipe(logError('Enseignant Dupont')),
            this.apiService
              .createEnseignant({ id: 0, nom: 'Martin', prenom: 'Luc', adresse: '5 avenue de Lyon, Lyon', codeVille: '69001', caution: 0, codeDepartement: 2 })
              .pipe(logError('Enseignant Martin')),
            this.apiService
              .createEtudiant({ id: 0, nom: 'Bernard', prenom: 'Sophie', adresse: '23 rue des Fleurs, Paris', codeVille: '75001', caution: 0, anneeUniversitaire: 2026 })
              .pipe(logError('Etudiant Bernard')),
            this.apiService
              .createEtudiant({ id: 0, nom: 'Lopez', prenom: 'Antoine', adresse: '42 bd de la Republique, Lyon', codeVille: '69001', caution: 0, anneeUniversitaire: 2025 })
              .pipe(logError('Etudiant Lopez')),
            this.apiService
              .createParticulier({ id: 0, nom: 'Rouge', prenom: 'Emma', adresse: '1 rue du Lac, Paris', codeVille: '75001', caution: 0 })
              .pipe(logError('Particulier Rouge')),
            this.apiService
              .createParticulier({ id: 0, nom: 'Petit', prenom: 'Jean', adresse: '14 place du Marche, Lyon', codeVille: '69001', caution: 0 })
              .pipe(logError('Particulier Petit')),
          ])
        ),

        catchError((err: unknown) => {
          console.error('[SEED] Erreur globale:', err);
          return of(null);
        })
      )
      .subscribe((results: SeedInsertResult | null) => {
        if (!results) return;
        const ok = results.filter(r => r !== null).length;
        const fail = results.length - ok;
        console.log(`[SEED] Termine: ${ok} insertions reussies, ${fail} echecs.`);
      });
  }
}