package org.leotalleceven.bibliotheque.models.utilisateur;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
//@Table(name="enseignants")
public class Enseignant extends Particulier {

    private int codeDepartement;

    public Enseignant() {
        super();
    }

    public Enseignant(int id, String nom, String prenom, String adresse, String codeVille, String email, int caution, int codeDepartement) {
        super(id, nom, prenom, adresse, codeVille, email, caution);
        this.codeDepartement = codeDepartement;
    }

    public int getCodeDepartement() {
        return codeDepartement;
    }

    public void setCodeDepartement(int codeDepartement) {
        this.codeDepartement = codeDepartement;
    }
}
