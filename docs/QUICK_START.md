# Guide de demarrage rapide

Ce document sert uniquement a lancer le projet en local en quelques minutes.
Pour l'architecture et le detail des choix techniques, voir les autres fichiers de documentation du projet.

## Prerequis

- Java 17+
- Maven
- Node.js et npm

## Demarrage

### 1. Lancer le backend

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

Frontend disponible sur `http://localhost:4200`.

## Verification rapide

- API: `http://localhost:8080/api/livres`
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`
- H2 Console: `http://localhost:8080/h2-console`

## Comptes de demonstration

### Bibliothecaire

- Email: `admin@bibliotheque.local`
- Mot de passe: `admin123`

### Utilisateurs deja presents

- `Rouge / Emma`
- `Petit / Jean`
- `Bernard / Sophie`
- `Dupont / Marie`

## En cas de probleme

- Verifier que le backend tourne bien sur le port `8080`
- Verifier que le frontend tourne bien sur le port `4200`
- Verifier que `proxy.conf.json` redirige `/api` vers `http://localhost:8080`
- Verifier dans le navigateur que les appels reseau vers `/api/...` repondent correctement

## Documents associes

- `API_INTEGRATION_SUMMARY.md`: details de communication front/back
- `IMPLEMENTATION_SUMMARY.md`: synthese des evolutions realisees

## Documentation en ligne

`https://cs2i-tallec-even-leo.github.io/BibliothequeSpringAngular/`
