import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// Models
import { Bibliotequaire } from '../model/Bibliotequaire';
import { Auteur } from '../model/Stock/Auteur';
import { Livre } from '../model/Stock/Livre';
import { Revue } from '../model/Stock/Revue';
import { Exemplaire } from '../model/Stock/Exemplaire';
import { Stockage } from '../model/Stock/Stockage';
import { Enseignant } from '../model/User/Enseignant';
import { Etudiant } from '../model/User/Etudiant';
import { Particulier } from '../model/User/Particulier';
import { Departement } from '../model/User/Departement';
import { Ville } from '../model/User/Ville';
import { Emprunt } from '../model/Emprunt';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  // ============ AUTEURS ============
  getAuteurs(): Observable<Auteur[]> {
    return this.http.get<Auteur[]>(`${this.apiUrl}/auteurs`);
  }

  getAuteur(id: number): Observable<Auteur> {
    return this.http.get<Auteur>(`${this.apiUrl}/auteurs/${id}`);
  }

  createAuteur(auteur: Auteur): Observable<Auteur> {
    return this.http.post<Auteur>(`${this.apiUrl}/auteurs`, auteur);
  }

  updateAuteur(id: number, auteur: Auteur): Observable<Auteur> {
    return this.http.put<Auteur>(`${this.apiUrl}/auteurs/${id}`, auteur);
  }

  deleteAuteur(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/auteurs/${id}`);
  }

  // ============ LIVRES ============
  getLivres(): Observable<Livre[]> {
    return this.http.get<Livre[]>(`${this.apiUrl}/livres`);
  }

  getLivre(id: number): Observable<Livre> {
    return this.http.get<Livre>(`${this.apiUrl}/livres/${id}`);
  }

  createLivre(livre: Livre): Observable<Livre> {
    return this.http.post<Livre>(`${this.apiUrl}/livres`, livre);
  }

  updateLivre(id: number, livre: Livre): Observable<Livre> {
    return this.http.put<Livre>(`${this.apiUrl}/livres/${id}`, livre);
  }

  deleteLivre(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/livres/${id}`);
  }

  // ============ REVUES ============
  getRevues(): Observable<Revue[]> {
    return this.http.get<Revue[]>(`${this.apiUrl}/revues`);
  }

  getRevue(id: number): Observable<Revue> {
    return this.http.get<Revue>(`${this.apiUrl}/revues/${id}`);
  }

  createRevue(revue: Revue): Observable<Revue> {
    return this.http.post<Revue>(`${this.apiUrl}/revues`, revue);
  }

  updateRevue(id: number, revue: Revue): Observable<Revue> {
    return this.http.put<Revue>(`${this.apiUrl}/revues/${id}`, revue);
  }

  deleteRevue(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/revues/${id}`);
  }

  // ============ EXEMPLAIRES ============
  getExemplaires(): Observable<Exemplaire[]> {
    return this.http.get<Exemplaire[]>(`${this.apiUrl}/exemplaires`);
  }

  getExemplaire(id: number): Observable<Exemplaire> {
    return this.http.get<Exemplaire>(`${this.apiUrl}/exemplaires/${id}`);
  }

  createExemplaire(exemplaire: Exemplaire): Observable<Exemplaire> {
    return this.http.post<Exemplaire>(`${this.apiUrl}/exemplaires`, exemplaire);
  }

  updateExemplaire(id: number, exemplaire: Exemplaire): Observable<Exemplaire> {
    return this.http.put<Exemplaire>(`${this.apiUrl}/exemplaires/${id}`, exemplaire);
  }

  deleteExemplaire(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/exemplaires/${id}`);
  }

  // ============ EMPRUNTS ============
  getEmprunts(): Observable<Emprunt[]> {
    return this.http.get<Emprunt[]>(`${this.apiUrl}/emprunts`);
  }

  getEmprunt(id: number): Observable<Emprunt> {
    return this.http.get<Emprunt>(`${this.apiUrl}/emprunts/${id}`);
  }

  createEmprunt(emprunt: Emprunt): Observable<Emprunt> {
    return this.http.post<Emprunt>(`${this.apiUrl}/emprunts`, emprunt);
  }

  updateEmprunt(id: number, emprunt: Emprunt): Observable<Emprunt> {
    return this.http.put<Emprunt>(`${this.apiUrl}/emprunts/${id}`, emprunt);
  }

  deleteEmprunt(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/emprunts/${id}`);
  }

  // ============ STOCKAGE ============
  getStockages(): Observable<Stockage[]> {
    return this.http.get<Stockage[]>(`${this.apiUrl}/stockages`);
  }

  getStockage(id: number): Observable<Stockage> {
    return this.http.get<Stockage>(`${this.apiUrl}/stockages/${id}`);
  }

  createStockage(stockage: Stockage): Observable<Stockage> {
    return this.http.post<Stockage>(`${this.apiUrl}/stockages`, stockage);
  }

  updateStockage(id: number, stockage: Stockage): Observable<Stockage> {
    return this.http.put<Stockage>(`${this.apiUrl}/stockages/${id}`, stockage);
  }

  deleteStockage(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/stockages/${id}`);
  }

  // ============ ENSEIGNANTS ============
  getEnseignants(): Observable<Enseignant[]> {
    return this.http.get<Enseignant[]>(`${this.apiUrl}/enseignants`);
  }

  getEnseignant(id: number): Observable<Enseignant> {
    return this.http.get<Enseignant>(`${this.apiUrl}/enseignants/${id}`);
  }

  createEnseignant(enseignant: Enseignant): Observable<Enseignant> {
    return this.http.post<Enseignant>(`${this.apiUrl}/enseignants`, enseignant);
  }

  updateEnseignant(id: number, enseignant: Enseignant): Observable<Enseignant> {
    return this.http.put<Enseignant>(`${this.apiUrl}/enseignants/${id}`, enseignant);
  }

  deleteEnseignant(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/enseignants/${id}`);
  }

  // ============ ETUDIANTS ============
  getEtudiants(): Observable<Etudiant[]> {
    return this.http.get<Etudiant[]>(`${this.apiUrl}/etudiants`);
  }

  getEtudiant(id: number): Observable<Etudiant> {
    return this.http.get<Etudiant>(`${this.apiUrl}/etudiants/${id}`);
  }

  createEtudiant(etudiant: Etudiant): Observable<Etudiant> {
    return this.http.post<Etudiant>(`${this.apiUrl}/etudiants`, etudiant);
  }

  updateEtudiant(id: number, etudiant: Etudiant): Observable<Etudiant> {
    return this.http.put<Etudiant>(`${this.apiUrl}/etudiants/${id}`, etudiant);
  }

  deleteEtudiant(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/etudiants/${id}`);
  }

  // ============ PARTICULIERS ============
  getParticuliers(): Observable<Particulier[]> {
    return this.http.get<Particulier[]>(`${this.apiUrl}/particuliers`);
  }

  getParticulier(id: number): Observable<Particulier> {
    return this.http.get<Particulier>(`${this.apiUrl}/particuliers/${id}`);
  }

  createParticulier(particulier: Particulier): Observable<Particulier> {
    return this.http.post<Particulier>(`${this.apiUrl}/particuliers`, particulier);
  }

  updateParticulier(id: number, particulier: Particulier): Observable<Particulier> {
    return this.http.put<Particulier>(`${this.apiUrl}/particuliers/${id}`, particulier);
  }

  deleteParticulier(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/particuliers/${id}`);
  }

  // ============ DEPARTEMENTS ============
  getDepartements(): Observable<Departement[]> {
    return this.http.get<Departement[]>(`${this.apiUrl}/departements`);
  }

  getDepartement(id: number): Observable<Departement> {
    return this.http.get<Departement>(`${this.apiUrl}/departements/${id}`);
  }

  createDepartement(departement: Departement): Observable<Departement> {
    return this.http.post<Departement>(`${this.apiUrl}/departements`, departement);
  }

  updateDepartement(id: number, departement: Departement): Observable<Departement> {
    return this.http.put<Departement>(`${this.apiUrl}/departements/${id}`, departement);
  }

  deleteDepartement(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/departements/${id}`);
  }

  // ============ VILLES ============
  getVilles(): Observable<Ville[]> {
    return this.http.get<Ville[]>(`${this.apiUrl}/villes`);
  }

  getVille(id: number): Observable<Ville> {
    return this.http.get<Ville>(`${this.apiUrl}/villes/${id}`);
  }

  createVille(ville: Ville): Observable<Ville> {
    return this.http.post<Ville>(`${this.apiUrl}/villes`, ville);
  }

  updateVille(id: number, ville: Ville): Observable<Ville> {
    return this.http.put<Ville>(`${this.apiUrl}/villes/${id}`, ville);
  }

  deleteVille(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/villes/${id}`);
  }
}
