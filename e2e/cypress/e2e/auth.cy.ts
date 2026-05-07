describe("Authentification", () => {
  beforeEach(() => {
    cy.visit("/connexion");
  });

  it("devrait afficher la page de connexion", () => {
    cy.contains("Connexion").should("exist");
    cy.get('input[id="nom"]').should("exist");
    cy.get('input[id="prenom"]').should("exist");
    cy.get('button[type="submit"]').should("exist");
  });

  it("devrait afficher une erreur avec des identifiants invalides", () => {
    cy.get('input[id="nom"]').type("invalide");
    cy.get('input[id="prenom"]').type("invalide");
    cy.get('button[type="submit"]').click();

    // Vérifier qu'on reste sur la page de connexion
    cy.url().should("include", "/connexion");
  });

  it("devrait se connecter avec des identifiants valides (utilisateur)", () => {
    cy.loginUser("Rouge", "Emma");

    // Vérifier la redirection vers la page d'accueil
    // Vérifier la redirection vers les emprunts
    cy.url().should("include", "/emprunts");
  });

  it("devrait se connecter avec des identifiants admin", () => {
    cy.loginAdmin("admin@bibliotheque.local", "admin123");

    // Vérifier la redirection vers l'espace admin
    // Vérifier la redirection après connexion admin
    cy.url().should("not.include", "/connexion-admin");
  });

  it("devrait se déconnecter correctement", () => {
    cy.loginUser("Rouge", "Emma");
    cy.logout();

    cy.url().should("include", "/connexion");
  });
});
