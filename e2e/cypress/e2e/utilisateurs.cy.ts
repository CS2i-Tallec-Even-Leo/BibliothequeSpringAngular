describe("Gestion des utilisateurs (Admin)", () => {
  beforeEach(() => {
    cy.loginAdmin("admin@bibliotheque.local", "admin123");
    cy.visit("/list-users");
  });

  it("devrait afficher la liste des utilisateurs", () => {
    cy.contains("Utilisateurs").should("exist");
    cy.get('table, [role="table"]').should("exist");
  });

  it("devrait afficher les colonnes du tableau utilisateurs", () => {
    cy.contains("Nom").should("exist");
    cy.contains("Email").should("exist");
    cy.contains("Type").should("exist");
  });

  it("devrait permettre de filtrer par type d'utilisateur", () => {
    cy.get('select, [role="combobox"]').first().select("Etudiant");

    cy.wait(500);

    cy.get('table tbody tr, [role="row"]').should("have.length.greaterThan", 0);
  });

  it("devrait afficher les détails d'un utilisateur", () => {
    cy.get('table tbody tr, [role="row"]').first().click();

    cy.url().should("include", "/utilisateurs/");
    cy.contains("Détails").should("exist");
  });

  it("devrait permettre d'ajouter un nouvel utilisateur", () => {
    cy.get("button").contains("Ajouter").click();

    cy.url().should("include", "/utilisateurs/creer");
    cy.get("form").should("exist");
  });

  it("devrait permettre d'éditer un utilisateur", () => {
    cy.get("button").contains("Éditer").first().click();

    cy.url().should("include", "/utilisateurs/");
    cy.get("form").should("exist");
  });

  it("devrait permettre de supprimer un utilisateur", () => {
    cy.get("button").contains("Supprimer").first().click();

    cy.get('.modal, [role="dialog"]').should("exist");
    cy.get("button").contains("Confirmer").click();

    cy.contains("Utilisateur supprimé").should("exist");
  });
});
