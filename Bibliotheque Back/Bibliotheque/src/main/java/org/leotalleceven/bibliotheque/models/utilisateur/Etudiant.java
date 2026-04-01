package org.leotalleceven.bibliotheque.models.utilisateur;

import jakarta.persistence.Entity;

@Entity
//@Table(name="etudiants")
public class Etudiant extends Particulier {

    private int anneeUniversitaire;

    public Etudiant() {
        super();
    }

    public Etudiant(int id, String nom, String prenom, String adresse, String codeVille, String email, int caution, int anneeUniversitaire) {
        super(id, nom, prenom, adresse, codeVille, email, caution);
        this.anneeUniversitaire = anneeUniversitaire;
    }

    public int getAnneeUniversitaire() {
        return anneeUniversitaire;
    }

    public void setAnneeUniversitaire(int anneeUniversitaire) {
        this.anneeUniversitaire = anneeUniversitaire;
    }
}
