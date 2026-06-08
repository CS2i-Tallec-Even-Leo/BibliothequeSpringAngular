# Guide de démarrage rapide pour les tests E2E

## ⚡ Démarrage rapide

### 1. Installer les dépendances

```bash
cd e2e
npm install
```

### 2. Exécuter les tests (démarrage automatique recommandé)

```bash
cd e2e
npm install
npm run cy:open
```

Cette commande démarre automatiquement:

- le backend sur `http://localhost:8080`
- le frontend sur `http://localhost:4200`
- puis ouvre Cypress

### 3. Démarrage manuel si nécessaire

```bash
cd "Bibliotheque-Back/Bibliotheque"
mvn spring-boot:run
```

### 4. Lancer le frontend (terminal 2)

```bash
cd "Bibliotheque-Front"
npm start
```

### 5. Exécuter les tests (terminal 3)

```bash
cd e2e
npm run cy:open
```

## 📋 Tests disponibles

| Fichier              | Description              | Durée estimée |
| -------------------- | ------------------------ | ------------- |
| `auth.cy.ts`         | Authentification         | ~30s          |
| `home.cy.ts`         | Page d'accueil           | ~30s          |
| `ressources.cy.ts`   | Gestion des ressources   | ~45s          |
| `emprunts.cy.ts`     | Gestion des emprunts     | ~45s          |
| `utilisateurs.cy.ts` | Gestion des utilisateurs | ~60s          |

## 🔑 Utilisateurs de test

```text
Utilisateur standard (nom + prénom):
   Nom: Rouge
   Prenom: Emma

Administrateur (email + mot de passe):
   Email: admin@bibliotheque.local
   Mot de passe: admin123
```

## 🎯 Commandes rapides

```bash
# Interface graphique (recommandé pour le développement)
npm run cy:open

# Exécuter tous les tests en headless
npm run cy:run

# Exécuter avec Chrome
npm run cy:run:chrome

# Exécuter en mode headless
npm run cy:run:headless
```

## 📊 Exécution complète

Pour exécuter TOUS les tests du début à la fin:

1. **Backend actif** sur `http://localhost:8080`
2. **Frontend actif** sur `http://localhost:4200`
3. Dans le dossier `e2e/` :

   ```bash
   npm run cy:run
   ```

Résultat: Rapport d'exécution complète avec statistiques et vidéos (si activé)

## 🐛 Déboguer un test

1. Ouvrez Cypress en mode interactif:

   ```bash
   npm run cy:open
   ```

2. Sélectionnez le test à déboguer

3. Utilisez les outils de dépannage:
   - **Step through**: Avancer pas à pas
   - **Pause**: Arrêter à un breakpoint
   - **Console**: Voir l'état des éléments
   - **Dev Tools**: Inspecter le DOM

## 📖 Documentation complète

Consultez [README.md](./README.md) pour une documentation détaillée.
