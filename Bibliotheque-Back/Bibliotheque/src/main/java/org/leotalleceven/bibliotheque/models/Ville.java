package org.leotalleceven.bibliotheque.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="villes")
public class Ville {

    @Id
    private int codeVille;
    private String nomVille;

    public Ville() {
    }

    public Ville(int codeVille, String nomVille) {
        this.codeVille = codeVille;
        this.nomVille = nomVille;
    }

    public int getCodeVille() {
        return codeVille;
    }

    public void setCodeVille(int codeVille) {
        this.codeVille = codeVille;
    }

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }
}
