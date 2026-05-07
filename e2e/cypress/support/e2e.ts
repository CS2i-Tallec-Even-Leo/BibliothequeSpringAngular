// Support fichier pour les tests E2E

// Importer les commandes custom
import "./commands";

// Configuration globale pour tous les tests
beforeEach(() => {
  // Réinitialiser l'état avant chaque test si nécessaire
  cy.clearCookies();
  cy.clearLocalStorage();
});

// Gérer les erreurs non capturées
Cypress.on("uncaught:exception", (err, runnable) => {
  // Retourner false pour empêcher Cypress de faire échouer le test
  return false;
});
