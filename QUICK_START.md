# Guide de Démarrage Rapide

## Prérequis
- Node.js et npm installés
- Java 17+ installé
- Maven installé
- Un IDE (VS Code, IntelliJ, Eclipse)

## Installation et Configuration

### 1. Backend (Spring Boot)

#### Étape 1: Accédez au dossier du backend
```bash
cd "Bibliotheque Back/Bibliotheque"
```

#### Étape 2: Compilez le projet
```bash
mvn clean install
```

#### Étape 3: Démarrez le serveur Spring Boot
```bash
mvn spring-boot:run
```

Le serveur doit être disponible sur: **http://localhost:8080**

### 2. Frontend (Angular)

#### Étape 1: Accédez au dossier du frontend
```bash
cd "Bibliotheque Front"
```

#### Étape 2: Installez les dépendances
```bash
npm install
```

#### Étape 3: Démarrez le serveur de développement
```bash
ng serve
```

ou

```bash
npm start
```

L'application doit être disponible sur: **http://localhost:4200**

## Vérification de la configuration

✅ **Backend opérationnel**: http://localhost:8080/api/livres
✅ **Frontend accessible**: http://localhost:4200
✅ **CORS activé**: Les requêtes du frontend vers le backend doivent fonctionner

## Les pages disponibles

### Frontend Routes
- **/** ou **/home** → Page d'accueil (statistiques)
- **/connexion** → Connexion utilisateur  
- **/connexion-admin** → Connexion administrateur
- **/create-user** → Créer un utilisateur
- **/list-users** → Liste des utilisateurs
- **/list-ressources** → Liste des ressources

### API Endpoints disponibles
- **GET/POST** `/api/auteurs` - Auteurs
- **GET/POST** `/api/livres` - Livres
- **GET/POST** `/api/revues` - Revues
- **GET/POST** `/api/exemplaires` - Exemplaires (NOUVEAU)
- **GET/POST** `/api/emprunts` - Emprunts (NOUVEAU)
- **GET/POST** `/api/stockages` - Stockages
- **GET/POST** `/api/enseignants` - Enseignants
- **GET/POST** `/api/etudiants` - Étudiants
- **GET/POST** `/api/particuliers` - Particuliers
- **GET/POST** `/api/departements` - Départements
- **GET/POST** `/api/villes` - Villes

## Structure des projets

```
BibliothequeSpringAngular/
├── Bibliotheque Back/
│   └── Bibliotheque/
│       ├── pom.xml
│       ├── src/
│       │   └── main/java/org/leotalleceven/bibliotheque/
│       │       ├── controller/          # Contrôleurs REST (API)
│       │       ├── models/              # Entités JPA
│       │       ├── repository/          # Repositories (accès données)
│       │       └── BibliothequeApplication.java
│       └── target/
│
├── Bibliotheque Front/
│   ├── src/
│   │   ├── app/
│   │   │   ├── services/              # Service API
│   │   │   ├── model/                 # Interfaces TypeScript
│   │   │   ├── connexion/             # Authentification
│   │   │   ├── home/                  # Page d'accueil
│   │   │   ├── create-user/           # Création utilisateur
│   │   │   ├── list-users/            # Liste utilisateurs
│   │   │   ├── list-ressources/       # Liste ressources
│   │   │   ├── app.ts                 # Composant root
│   │   │   ├── app.routes.ts          # Routing
│   │   │   └── app.config.ts          # Configuration Angular
│   │   ├── index.html
│   │   └── main.ts
│   ├── package.json
│   └── angular.json
│
├── API_INTEGRATION_SUMMARY.md          # Documentation intégration
└── QUICK_START.md                      # Ce fichier
```

## Workflow typique

### 1. Créer un utilisateur
```
Frontend: Remplir formulaire create-user
  ↓
ApiService: POST /api/etudiants
  ↓
Backend: EtudiantController.createEtudiant()
  ↓
Base de donnée: INSERT
  ↓
Réponse au frontend: 201 Created
  ↓
Frontend: Redirection vers list-users
```

### 2. Afficher la liste des ressources
```
Frontend: Composant list-ressources
  ↓
ApiService: GET /api/livres, /api/revues, /api/auteurs
  ↓
Backend: Controllers retournent les données
  ↓
Frontend: Affiche dans les tableaux
```

## Dépannage courant

### Le frontend ne peut pas atteindre le backend
**Problème**: Erreur CORS ou connection refused
**Solution**:
1. Vérifiez que le backend est bien en train de tourner sur le port 8080
2. Vérifiez que `@CrossOrigin(origins = "http://localhost:4200")` est présent dans les contrôleurs
3. Vérifiez la console du navigateur pour les erreurs CORS

### Les données ne s'affichent pas
**Problème**: Requête API réussit mais pas d'affichage
**Solution**:
1. Vérifiez la console du navigateur (F12) pour les erreurs
2. Vérifiez que les modèles TypeScript correspondent aux données retournées
3. Utilisez Angular DevTools pour inspecter le composant

### Erreur 404 sur l'API
**Problème**: Endpoint non trouvé
**Solution**:
1. Vérifiez que le contrôleur est bien créé
2. Vérifiez l'url: `/api/ressource`
3. Vérifiez que @RequestMapping est correct

## Prochaines étapes

1. **Authentification réelle**: Implémenter JWT token
2. **Validation améliorée**: Ajouter des validateurs côté client et serveur
3. **Gestion d'erreurs**: Créer un service dédié aux notifications
4. **Base de données**: Configurer correctement la BDD (MySQL, PostgreSQL)
5. **Tests unitaires**: Ajouter des tests pour les services et composants
6. **Documentation API**: Générer la documentation Swagger

## Ressources utiles

- [Documentation Angular](https://angular.io)
- [Documentation Spring Boot](https://spring.io/projects/spring-boot)
- [HTTP Client Angular](https://angular.io/guide/http)
- [Jakarta Persistence API](https://jakarta.ee/specifications/persistence/)

## Contacter pour support

Pour toute question sur l'intégration de l'API, consultez le fichier `API_INTEGRATION_SUMMARY.md` pour plus de détails techniques.
