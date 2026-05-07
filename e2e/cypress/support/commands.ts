// Commandes personnalisées pour les tests E2E

/**
 * Commande pour se connecter comme utilisateur (firstname + lastname)
 */
Cypress.Commands.add("loginUser", (firstname: string, lastname: string) => {
  cy.visit("/connexion");
  cy.get('input[id="nom"]').clear().type(firstname);
  cy.get('input[id="prenom"]').clear().type(lastname);
  cy.get('button[type="submit"]').click();
  cy.url().should("include", "/emprunts");
});

/**
 * Commande pour se connecter comme administrateur (email + password)
 */
Cypress.Commands.add("loginAdmin", (email: string, password: string) => {
  cy.visit("/connexion-admin");
  cy.get('input[id="email"]').clear().type(email);
  cy.get('input[id="password"]').clear().type(password);
  cy.get('button[type="submit"]').click();
  cy.url().should("include", "/list-ressources");
});

/**
 * Commande pour se connecter (alias pour loginUser)
 */
Cypress.Commands.add("login", (firstname: string, lastname: string) => {
  cy.loginUser(firstname, lastname);
});

/**
 * Commande pour se déconnecter
 */
Cypress.Commands.add("logout", () => {
  cy.visit("/home");
  cy.get("button").contains("Déconnexion", { matchCase: false }).click();
  cy.url().should("include", "/connexion");
});

/**
 * Commande pour vérifier la présence d'un élément
 */
Cypress.Commands.add("shouldExist", (selector: string) => {
  cy.get(selector).should("exist").and("be.visible");
});

/**
 * Commande pour attendre le chargement de l'API
 */
Cypress.Commands.add("waitForAPI", () => {
  cy.intercept("GET", "/api/**").as("apiCall");
  cy.wait("@apiCall", { timeout: 10000 });
});

declare global {
  namespace Cypress {
    interface Chainable {
      login(firstname: string, lastname: string): Chainable;
      loginUser(firstname: string, lastname: string): Chainable;
      loginAdmin(email: string, password: string): Chainable;
      logout(): Chainable;
      shouldExist(selector: string): Chainable;
      waitForAPI(): Chainable;
    }
  }
}

export {};
