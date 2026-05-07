# Tests End-to-End (E2E) - Bibliotheque

## Vue d'ensemble

Ce dossier contient les tests end-to-end pour l'application Bibliotheque Spring Angular, utilisant **Cypress** comme framework de test.

Les tests couvrent les flux utilisateur principaux :

- ✅ Authentification (login/logout)
- ✅ Navigation et page d'accueil
- ✅ Gestion des ressources (livres, revues)
- ✅ Gestion des emprunts
- ✅ Gestion des utilisateurs (admin)

## Structure du projet

```text
e2e/
├── cypress/
│   ├── e2e/                 # Tests E2E
│   │   ├── auth.cy.ts       # Tests d'authentification
│   │   ├── home.cy.ts       # Tests de la page d'accueil
│   │   ├── ressources.cy.ts # Tests de gestion des ressources
│   │   ├── emprunts.cy.ts   # Tests de gestion des emprunts
│   │   └── utilisateurs.cy.ts # Tests de gestion des utilisateurs
│   ├── fixtures/            # Données de test
│   │   └── users.json       # Utilisateurs de test
│   └── support/             # Configuration et commandes
│       ├── e2e.ts           # Configuration globale
│       └── commands.ts      # Commandes personnalisées
├── cypress.config.ts        # Configuration Cypress
└── package.json             # Dépendances E2E
```

## Installation

### 1. Installer les dépendances

```bash
cd e2e
npm install
```

### 2. Configurer le backend et frontend

Assurez-vous que le backend et le frontend s'exécutent :

**Terminal 1 - Backend:**

```bash
cd "Bibliotheque Back/Bibliotheque"
mvn spring-boot:run
```

**Terminal 2 - Frontend:**

```bash
cd "Bibliotheque Front"
npm start
```

Le backend doit s'exécuter sur `http://localhost:8080`
Le frontend doit s'exécuter sur `http://localhost:4200`

## Utilisation

### Mode interactif (avec interface graphique)

```bash
npm run cy:open
```

Cela ouvrira l'interface Cypress où vous pouvez :

- Sélectionner les tests à exécuter
- Voir les tests s'exécuter en temps réel
- Déboguer chaque étape

### Mode headless (sans interface)

```bash
npm run cy:run
```

Exécute tous les tests et génère un rapport.

### Options spécifiques

```bash
# Exécuter avec Chrome
npm run cy:run:chrome

# Exécuter avec Firefox
npm run cy:run:firefox

# Mode headless complet
npm run cy:run:headless
```

## Fichiers de test

### `auth.cy.ts` - Authentification

Tests des flux d'authentification :

- Affichage de la page de connexion
- Erreurs avec identifiants invalides
- Connexion réussie
- Déconnexion

**Utilisateurs de test par défaut :**

- `user1` / `password123` (utilisateur standard)
- `admin` / `admin123` (administrateur)

### `home.cy.ts` - Page d'accueil

Tests de la page d'accueil :

- Affichage du contenu principal
- Navigation vers les ressources
- Navigation vers les emprunts

### `ressources.cy.ts` - Gestion des ressources

Tests de gestion des ressources :

- Affichage de la liste
- Recherche
- Filtrage par type
- Consultation des détails

### `emprunts.cy.ts` - Gestion des emprunts

Tests de gestion des emprunts :

- Affichage de la liste
- Filtrage (actifs/retournés)
- Création d'emprunt
- Retour d'exemplaire
- Recherche

### `utilisateurs.cy.ts` - Gestion des utilisateurs

Tests d'administration (nécessite rôle admin) :

- Affichage de la liste
- Filtrage par type
- Consultation des détails
- Création d'utilisateur
- Édition d'utilisateur
- Suppression d'utilisateur

## Commandes personnalisées

Les commandes suivantes sont disponibles dans les tests :

```typescript
// Connexion utilisateur (prénom, nom)
cy.loginUser("Rouge", "Emma");

// Connexion administrateur (email, mot de passe)
cy.loginAdmin("admin@bibliotheque.local", "admin123");

// Alias pour connexion utilisateur
cy.login("Rouge", "Emma");

// Déconnexion
cy.logout();

// Vérifier la présence d'un élément
cy.shouldExist("selector");

// Attendre la réponse API
cy.waitForAPI();
```

## Configuration avancée

### Modifier le port du serveur

Si votre application s'exécute sur un port différent, modifiez `cypress.config.ts` :

```typescript
export default defineConfig({
  e2e: {
    baseUrl: "http://localhost:4200", // Changer le port si nécessaire
    // ...
  },
});
```

### Augmenter les délais d'attente

Pour les machines lentes, augmentez les timeouts :

```typescript
defaultCommandTimeout: 10000,      // 10 secondes
requestTimeout: 15000,             // 15 secondes
responseTimeout: 15000,
```

## Ajouter de nouveaux tests

1. Créez un nouveau fichier `.cy.ts` dans `cypress/e2e/`
2. Utilisez la structure standard Cypress :

```typescript
describe("Nom de la fonctionnalité", () => {
  beforeEach(() => {
    cy.login("user1", "password123");
    cy.visit("/path");
  });

  it("devrait faire quelque chose", () => {
    cy.get("[selector]").click();
    cy.contains("expected text").should("exist");
  });
});
```

1. Exécutez les tests avec `npm run cy:open`

## Dépannage

### Les tests ne trouvent pas les éléments

- Vérifiez que les sélecteurs CSS sont corrects
- Utilisez `cy.debug()` pour inspecter l'état de la page
- Augmentez les délais d'attente

### Erreurs de connexion

- Assurez-vous que le backend s'exécute sur port 8080
- Vérifiez les identifiants user/password
- Consultez les logs d'erreur dans la console Cypress

### Problèmes de proxying

Vérifiez que `Bibliotheque Front/proxy.conf.json` configure correctement le proxy vers l'API.

## Documentation

- [Cypress Official Docs](https://docs.cypress.io)
- [Repository Guide](../README.md)

## Bonnes pratiques

✅ **À faire :**

- Utiliser les données de test du fichier `fixtures/users.json`
- Garder les tests indépendants (chaque test doit être autonome)
- Utiliser les commandes personnalisées
- Attendre explicitement les éléments dynamiques

❌ **À éviter :**

- Hardcoder les données de test dans les tests
- Dépendre d'autres tests pour préparer l'état
- Utiliser des délais fixes (`cy.wait(1000)`) au lieu d'attendre les éléments
- Tester l'implémentation plutôt que le comportement utilisateur
