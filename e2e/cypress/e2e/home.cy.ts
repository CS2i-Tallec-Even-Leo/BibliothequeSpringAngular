describe("Page d'accueil", () => {
  beforeEach(() => {
    cy.loginUser("Rouge", "Emma");
    cy.visit("/home");
  });

  it("devrait afficher la page d'accueil correctement", () => {
    cy.contains("Accueil").should("exist");
    cy.contains("Bienvenue").should("exist");
  });

  it("devrait afficher les sections principales", () => {
    // Vérifier la présence des sections principales
    cy.get("nav").should("exist");
    cy.contains("Ressources").should("exist");
    cy.contains("Emprunts").should("exist");
  });

  it("devrait naviguer vers la liste des ressources", () => {
    cy.contains("Ressources").click();
    cy.url().should("include", "/list-ressources");
  });

  it("devrait naviguer vers la liste des emprunts", () => {
    cy.contains("Emprunts").click();
    cy.url().should("include", "/emprunts");
  });
});
