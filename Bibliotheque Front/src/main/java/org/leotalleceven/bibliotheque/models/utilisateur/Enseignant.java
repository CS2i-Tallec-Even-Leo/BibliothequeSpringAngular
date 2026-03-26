package org.leotalleceven.bibliotheque.models.utilisateur;

public class Enseignant extends Particulier {

    private int codeDepartement;

    public Enseignant(int id, String nom, String prenom, String adresse, String codeVille, int caution, int codeDepartement) {
        super(id, nom, prenom, adresse, codeVille, caution);
        this.codeDepartement = codeDepartement;
    }

    public int getCodeDepartement() {
        return codeDepartement;
    }

    public void setCodeDepartement(int codeDepartement) {
        this.codeDepartement = codeDepartement;
    }
}
