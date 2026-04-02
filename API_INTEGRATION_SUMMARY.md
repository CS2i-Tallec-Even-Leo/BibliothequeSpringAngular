# Intégration API - Résumé des modifications

## Vue d'ensemble

Ce document résume les modifications apportées pour intégrer la communication entre le frontend Angular et le backend Spring Boot.

## Modifications Frontend (Angular)

### 1. **ApiService** (`src/app/services/api.ts`)

- URL de base: `http://localhost:8080/api`
- Méthodes CRUD pour tous les endpoints:
  - **Auteurs** (`/api/auteurs`)
  - **Livres** (`/api/livres`)
  - **Revues** (`/api/revues`)
  - **Exemplaires** (`/api/exemplaires`) - NOUVEAU
  - **Emprunts** (`/api/emprunts`) - NOUVEAU
  - **Stockages** (`/api/stockages`)
  - **Enseignants** (`/api/enseignants`)
  - **Étudiants** (`/api/etudiants`)
  - **Particuliers** (`/api/particuliers`)
  - **Départements** (`/api/departements`)
  - **Villes** (`/api/villes`)

### 2. **Configuration HTTP** (`src/app/app.config.ts`)

- Configuration de `HttpClient` avec support XSRF
- Les requêtes HTTP sont maintenant correctement configurées

### 3. **Routing** (`src/app/app.routes.ts`)

- Route par défaut: `/home`
- Routes disponibles:
  - `/home` - Page d'accueil
  - `/connexion` - Connexion utilisateur
  - `/connexion-admin` - Connexion administrateur
  - `/create-user` - Créer un utilisateur
  - `/list-users` - Liste des utilisateurs
  - `/list-ressources` - Liste des ressources

### 4. **Composants mis à jour**

#### Home (`src/app/home/`)

- Affiche les statistiques de toutes les ressources
- Charge les données depuis l'API au démarrage
- Navigation vers les autres pages
- Fonction de déconnexion

#### Connexion (`src/app/connexion/`)

- Formulaire de connexion utilisateur
- Sauvegarde les données dans localStorage
- Redirection vers `/home` après connexion

#### Connexion Admin (`src/app/connexion-admin/`)

- Formulaire de connexion administrateur
- Rôle ADMIN sauvegardé dans localStorage

#### Create User (`src/app/create-user/`)

- Formulaire pour créer des utilisateurs (Étudiant, Enseignant, Particulier)
- Charge dynamiquement les départements et villes depuis l'API
- Validation des champs requis
- Redirection après création succès

#### List Users (`src/app/list-users/`)

- Affichage des utilisateurs en listes tabulaires
- Onglets pour Enseignants, Étudiants, Particuliers
- Suppression d'utilisateurs
- Chargement à la demande des données

#### List Ressources (`src/app/list-ressources/`)

- Affichage des ressources (Livres, Revues, Auteurs)
- Onglets pour chaque type de ressource
- Suppression de ressources
- Chargement à la demande des données

## Modifications Backend (Spring Boot)

### 1. **Nouveaux Modèles**

#### Exemplaire (`models/lecture/Exemplaire.java`)

```java
- id (Integer)
- numeroExemplaire (String)
- etat (String)
- ressource (Ressource) - Many-to-One
- dateAcquisition (String)
```

#### Emprunt (`models/Emprunt.java`)

```java
- id (Integer)
- dateEmprunt (LocalDate)
- dateRetourPrevue (LocalDate)
- dateRetourEffectif (LocalDate)
- statut (String)
- particulier (Particulier) - Many-to-One
- exemplaire (Exemplaire) - Many-to-One
```

### 2. **Nouveaux Repositories**

- `ExemplaireRepository extends JpaRepository<Exemplaire, Integer>`
- `EmpruntRepository extends JpaRepository<Emprunt, Integer>`

### 3. **Nouveaux Controllers**

#### ExemplaireController (`/api/exemplaires`)

- GET `/api/exemplaires` - Récupérer tous les exemplaires
- GET `/api/exemplaires/{id}` - Récupérer un exemplaire
- POST `/api/exemplaires` - Créer un exemplaire
- PUT `/api/exemplaires/{id}` - Modifier un exemplaire
- DELETE `/api/exemplaires/{id}` - Supprimer un exemplaire

#### EmpruntController (`/api/emprunts`)

- GET `/api/emprunts` - Récupérer tous les emprunts
- GET `/api/emprunts/{id}` - Récupérer un emprunt
- POST `/api/emprunts` - Créer un emprunt
- PUT `/api/emprunts/{id}` - Modifier un emprunt
- DELETE `/api/emprunts/{id}` - Supprimer un emprunt

### 4. **Configuration CORS**

Tous les controllers ont le CORS activé pour `http://localhost:4200`:

```java
@CrossOrigin(origins = "http://localhost:4200")
```

## Architecture de Communication

```sh
Frontend Angular
    ↓
HttpClient/ApiService
    ↓
Spring Boot API (localhost:8080)
    ↓
JPA Repositories
    ↓
Base de données
```

## Flux de données typique

### Exemple: Créer un utilisateur

1. Formulaire dans `create-user.component`
2. Submit → `createEtudiant()` dans `ApiService`
3. POST `/api/etudiants` avec les données
4. Spring reçoit et sauvegarde
5. Réponse retournée au frontend
6. Message de succès et redirection

### Exemple: Afficher la liste des ressources

1. `list-ressources.component` - `ngOnInit()`
2. Appelle `getLivres()`, `getRevues()`, `getAuteurs()`
3. Les observables récupèrent les données
4. Les données sont affichées dans les tableaux
5. Onglets permettent de switcher entre les vues

## Points d'attention

⚠️ **Authentification**

- Les formulaires de connexion sont actuellement des mocks (localStorage)
- À remplacer avec une vraie authentification JWT/OAuth2

⚠️ **Gestion d'erreurs**

- Les erreurs API sont loggées en console
- À améliorer avec un service dédié aux notifications d'erreur

⚠️ **Validation**

- Validation basique avec les attributs HTML `required`
- À enrichir avec des validateurs Angular Reactive Forms

## Commandes utiles

### Démarrer le backend

```bash
cd "Bibliotheque Back/Bibliotheque"
mvn spring-boot:run
```

### Démarrer le frontend

```bash
cd "Bibliotheque Front"
npm install
ng serve
```

### URL d'accès

- Frontend: <http://localhost:4200>
- Backend API: <http://localhost:8080/api>

## Prochaines étapes recommandées

1. ✅ Implémenter l'authentification réelle (JWT)
2. ✅ Ajouter des validations Reactive Forms
3. ✅ Créer un interceptor HTTP pour gérer les tokens
4. ✅ Implémenter un service de notifications
5. ✅ Améliorer la gestion des erreurs
6. ✅ Ajouter des confirmations de suppression
7. ✅ Implémenter les relations entre entités (JoinTable)
8. ✅ Créer des DTOs pour les transferts de données
