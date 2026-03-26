package org.leotalleceven.bibliotheque.models;

public class Departement {

    private int codeDepartement;
    private String nomDepartement;

    public Departement(int codeDepartement, String nomDepartement) {
        this.codeDepartement = codeDepartement;
        this.nomDepartement = nomDepartement;
    }

    public int getCodeDepartement() {
        return codeDepartement;
    }

    public void setCodeDepartement(int codeDepartement) {
        this.codeDepartement = codeDepartement;
    }

    public String getNomDepartement() {
        return nomDepartement;
    }

    public void setNomDepartement(String nomDepartement) {
        this.nomDepartement = nomDepartement;
    }
}
