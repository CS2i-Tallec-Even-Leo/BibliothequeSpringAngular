package org.leotalleceven.bibliotheque.models.utilisateur;

public class Etudiant extends Particulier {

    private int anneeUniversitaire;

    public Etudiant(int id, String nom, String prenom, String adresse, String codeVille, int caution, int anneeUniversitaire) {
        super(id, nom, prenom, adresse, codeVille, caution);
        this.anneeUniversitaire = anneeUniversitaire;
    }

    public int getAnneeUniversitaire() {
        return anneeUniversitaire;
    }

    public void setAnneeUniversitaire(int anneeUniversitaire) {
        this.anneeUniversitaire = anneeUniversitaire;
    }
}
