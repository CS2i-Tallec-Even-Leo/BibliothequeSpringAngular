describe("Gestion des ressources", () => {
  beforeEach(() => {
    cy.loginAdmin("admin@bibliotheque.local", "admin123");
    cy.visit("/list-ressources");
  });

  it("devrait afficher la liste des ressources", () => {
    cy.contains("Ressources").should("exist");
    cy.get('table, [role="table"]').should("exist");
  });

  it("devrait afficher les colonnes du tableau", () => {
    cy.contains("Titre").should("exist");
    cy.contains("Type").should("exist");
    cy.contains("Auteur").should("exist");
  });

  it("devrait permettre de rechercher une ressource", () => {
    cy.get('input[type="search"]').first().type("livre");

    // Attendre que les résultats se chargent
    cy.wait(500);

    // Vérifier que le tableau se met à jour
    cy.get('table tbody tr, [role="row"]').should("have.length.greaterThan", 0);
  });

  it("devrait afficher les détails d'une ressource au clic", () => {
    cy.get('table tbody tr, [role="row"]').first().click();

    // Vérifier la navigation vers les détails
    cy.url().should("include", "/ressources/");
    cy.contains("Détails").should("exist");
  });

  it("devrait filtrer par type de ressource", () => {
    cy.get('select, [role="combobox"]').first().select("Livre");

    // Attendre la mise à jour
    cy.wait(500);

    // Vérifier que les résultats sont filtrés
    cy.get('table tbody tr, [role="row"]').should("have.length.greaterThan", 0);
  });
});
