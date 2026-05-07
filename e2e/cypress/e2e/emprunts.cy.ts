describe("Gestion des emprunts", () => {
  beforeEach(() => {
    cy.loginUser("Rouge", "Emma");
    cy.visit("/emprunts");
  });

  it("devrait afficher la liste des emprunts", () => {
    cy.contains("Emprunts").should("exist");
    cy.get('table, [role="table"]').should("exist");
  });

  it("devrait afficher les colonnes du tableau d'emprunts", () => {
    cy.contains("Ressource").should("exist");
    cy.contains("Date d'emprunt").should("exist");
    cy.contains("Date de retour").should("exist");
  });

  it("devrait afficher les emprunts actifs et retournés", () => {
    cy.get('button, [role="tab"]').contains("Actifs").click();
    cy.get('table tbody tr, [role="row"]').should("have.length.greaterThan", 0);

    cy.get('button, [role="tab"]').contains("Retournés").click();
    cy.get('table tbody tr, [role="row"]').should("have.length.greaterThan", 0);
  });

  it("devrait permettre de créer un nouvel emprunt", () => {
    cy.get("button").contains("Nouvel emprunt").click();

    cy.url().should("include", "/emprunts/creer");
    cy.get("form").should("exist");
  });

  it("devrait permettre de retourner un exemplaire", () => {
    cy.get("button").contains("Retourner").first().click();

    // Vérifier la confirmation
    cy.get('.modal, [role="dialog"]').should("exist");
    cy.get("button").contains("Confirmer").click();

    // Vérifier que l'emprunt a été mis à jour
    cy.contains("Emprunt retourné").should("exist");
  });

  it("devrait permettre de rechercher un emprunt", () => {
    cy.get('input[type="search"]').first().type("livre");

    cy.wait(500);

    cy.get('table tbody tr, [role="row"]').should("have.length.greaterThan", 0);
  });
});
