# Integration API

Ce document decrit uniquement la communication entre le frontend Angular et le backend Spring Boot.

## Principe general

```text
Frontend Angular
  -> ApiService
  -> requetes HTTP sur /api
  -> proxy Angular
  -> backend Spring Boot sur http://localhost:8080
  -> repositories JPA
  -> base H2
```

## Point d'entree frontend

Le frontend centralise les appels HTTP dans `src/app/services/api.ts`.

- URL de base utilisee: `/api`
- En developpement, `proxy.conf.json` redirige `/api` vers `http://localhost:8080`
- Les composants consomment uniquement `ApiService`, sans appeler directement l'API

## Ressources exposees

### Authentification

- `POST /api/auth/user-login`
- `POST /api/auth/admin-login`

### Catalogue et stock

- `/api/auteurs`
- `/api/livres`
- `/api/revues`
- `/api/exemplaires`
- `/api/stockages`

### Utilisateurs et emprunts

- `/api/enseignants`
- `/api/etudiants`
- `/api/particuliers`
- `/api/emprunts`
- `/api/departements`
- `/api/villes`

## Composants relies a l'API

- `home`: charge les statistiques et les donnees de synthese
- `connexion`: connecte un utilisateur via l'API d'authentification
- `connexion-admin`: connecte un bibliothecaire
- `create-user`: cree des utilisateurs et charge les referentiels
- `list-users`: recupere et supprime des utilisateurs
- `list-ressources`: recupere et supprime des ressources
- `emprunt`: cree, liste et met a jour les emprunts

## Comportements d'integration importants

- Les donnees d'authentification sont stockees cote navigateur dans `localStorage`
- Le backend autorise `http://localhost:4200` via `@CrossOrigin`
- Les nouvelles entites `Exemplaire` et `Emprunt` sont exposees en CRUD
- Le service d'emprunt expose aussi des routes de retour et de gestion des retards

## Flux typiques

### Connexion utilisateur

1. Le formulaire envoie `nom` et `prenom` a `POST /api/auth/user-login`.
2. Le backend recherche un utilisateur existant.
3. Le frontend enregistre la reponse dans `localStorage`.

### Creation d'un utilisateur

1. Le composant charge d'abord departements et villes.
2. Le formulaire appelle le bon endpoint selon le type d'utilisateur.
3. La reponse est reaffichee dans l'interface puis la navigation continue.

### Gestion des emprunts

1. Le frontend charge particuliers et exemplaires.
2. La creation appelle `POST /api/emprunts`.
3. Le retour d'un exemplaire passe par une route dediee du backend.

## Limites actuelles de l'integration

- Pas de JWT ni de session securisee
- Gestion d'erreurs encore simple cote frontend
- Les validations sont essentiellement fonctionnelles, pas encore centralisees

## A consulter ensuite

- `QUICK_START.md` pour lancer le projet
- `IMPLEMENTATION_SUMMARY.md` pour la synthese des changements
- `MODIFICATIONS_CHECKLIST.md` pour le suivi detaille
