# BibliothequeSpringAngular

Application de gestion de bibliotheque composee d'un backend Spring Boot et d'un frontend Angular.

Le projet permet de consulter et gerer les ressources d'une bibliotheque, les utilisateurs, les exemplaires et les emprunts via une interface web connectee a une API REST.

Lien vers le [Swagger](https://cs2i-tallec-even-leo.github.io/BibliothequeSpringAngular/swagger/)

## Apercu

- Backend: Spring Boot 4, Spring Web, Spring Data JPA, H2, Springdoc OpenAPI
- Frontend: Angular 21, standalone components, HttpClient, router
- Base de donnees: H2 persistante sur fichier local
- Communication front/back: proxy Angular sur `/api` vers `http://localhost:8080`

## Fonctionnalites principales

- Authentification simple utilisateur et bibliothecaire
- Consultation des livres, revues et auteurs
- Gestion des utilisateurs: etudiants, enseignants, particuliers
- Gestion des exemplaires et des emprunts
- Recherche de livres via l'API
- Documentation OpenAPI et interface Swagger
- Donnees de demo initialisees automatiquement au premier lancement

## Demarrage rapide

### 1. Lancer le backend

Depuis la racine du workspace:

```bash
cd BibliothequeSpringAngular/Bibliotheque-Back/Bibliotheque
sudo apt install default-jre
sudo apt install maven
mvn spring-boot:run
```

Backend disponible sur `http://localhost:8080`.

### 2. Lancer le frontend

```bash
cd ../../Bibliotheque-Front/
sudo apt install nodejs npm -y
npm install
npm start
```

Le frontend demarre sur `http://localhost:4200`.

### 3. Verifier que tout fonctionne

- Frontend: `http://localhost:4200`
- API REST: `http://localhost:8080/api`
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`
- Console H2: `http://localhost:8080/h2-console`

## Architecture

```text
BibliothequeSpringAngular/
|-- Bibliotheque-Back/
|   \-- Bibliotheque/        # API Spring Boot
|-- Bibliotheque-Front/      # Application Angular
|-- QUICK_START.md
|-- API_INTEGRATION_SUMMARY.md
|-- IMPLEMENTATION_SUMMARY.md
\-- MODIFICATIONS_CHECKLIST.md
```

## Stack technique

### Backend

- Java 17
- Spring Boot 4.0.4
- Spring Web
- Spring Data JPA
- H2 Database
- Springdoc OpenAPI
- Maven

### Frontend

- Angular 21.2
- TypeScript 5.9
- RxJS 7.8
- Vitest
- npm 11

## Prerequis

- Java 17 ou plus
- Maven 3.9 ou plus
- Node.js 20 ou plus
- npm

## Configuration actuelle

### Backend Configuration

Le backend utilise une base H2 sur fichier local:

```properties
spring.datasource.url=jdbc:h2:file:./bibliothequedb
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
```

La base est persistante entre les redemarrages de l'application.

### Frontend Configuration

Le frontend appelle l'API avec une URL relative `/api`.
En developpement, le fichier `proxy.conf.json` redirige ces requetes vers `http://localhost:8080`.

## Authentification

Le projet utilise une authentification simple, sans JWT.

### Connexion bibliothecaire

- Endpoint: `POST /api/auth/admin-login`
- Identifiants par defaut:
  - Email: `admin@bibliotheque.local`
  - Mot de passe: `admin123`

### Connexion utilisateur

- Endpoint: `POST /api/auth/user-login`
- Le login se fait avec le couple `nom` + `prenom` d'un utilisateur deja present en base.

Exemples de donnees initialisees:

- `Rouge / Emma`
- `Petit / Jean`
- `Bernard / Sophie`
- `Dupont / Marie`

## Donnees de demo initialisees

Au premier lancement, le backend cree automatiquement un jeu de donnees comprenant notamment:

- 10 livres
- 3 revues
- 3 auteurs
- 2 departements
- 2 villes
- 2 enseignants
- 2 etudiants
- 2 particuliers
- des exemplaires associes aux ressources

Cela permet de tester l'application sans preparation supplementaire.

## Pages disponibles

- `/home`: page d'accueil et statistiques
- `/connexion`: connexion utilisateur
- `/connexion-admin`: connexion bibliothecaire
- `/create-user`: creation d'utilisateur
- `/list-users`: gestion des utilisateurs
- `/list-ressources`: gestion des ressources
- `/emprunts`: gestion des emprunts

## API principale

L'API expose notamment les ressources suivantes:

- `/api/auth`
- `/api/auteurs`
- `/api/livres`
- `/api/revues`
- `/api/exemplaires`
- `/api/emprunts`
- `/api/stockages`
- `/api/enseignants`
- `/api/etudiants`
- `/api/particuliers`
- `/api/departements`
- `/api/villes`

La plupart des endpoints CRUD supportent `GET`, `POST`, `PUT` et `DELETE`.

## Commandes utiles

### Backend Commandes

```bash
cd "Bibliotheque-Back/Bibliotheque"
mvn clean install
mvn test
mvn spring-boot:run
```

### Frontend Commandes

```bash
cd "Bibliotheque-Front"
npm install
npm start
npm run build
npm test
```

## Structure du code

### Backend Structure

- `src/main/java/.../controller`: endpoints REST
- `src/main/java/.../models`: entites metier
- `src/main/java/.../repository`: acces aux donnees
- `src/main/resources/application.properties`: configuration applicative
- `DataInitializer.java`: jeu de donnees initialise au demarrage

### Frontend Structure

- `src/app/services`: service d'appel API
- `src/app/model`: modeles TypeScript
- `src/app/home`: tableau de bord
- `src/app/connexion`: connexion utilisateur
- `src/app/connexion-admin`: connexion bibliothecaire
- `src/app/create-user`: creation utilisateur
- `src/app/list-users`: liste et suppression d'utilisateurs
- `src/app/list-ressources`: liste et suppression de ressources
- `src/app/emprunt`: gestion des emprunts

## Documentation du projet

- `QUICK_START.md`: demarrage rapide
- `API_INTEGRATION_SUMMARY.md`: details de l'integration front/back
- `IMPLEMENTATION_SUMMARY.md`: synthese des evolutions implementees

## Limites actuelles

- Authentification simplifiee, sans gestion de session securisee
- Pas de JWT ou OAuth2
- Gestion d'erreurs encore basique cote frontend
- Projet configure principalement pour un usage local et de demonstration

## Tests et validation

Le backend contient deja des tests Spring Boot et de controleurs.
Le frontend utilise l'outillage de test Angular avec Vitest.

## Auteur

Projet de bibliotheque full stack Spring Boot + Angular pour experimentation, apprentissage et demonstration autour d'une API REST et d'une interface d'administration simple.
