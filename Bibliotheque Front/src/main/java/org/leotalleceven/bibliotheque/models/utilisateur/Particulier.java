package org.leotalleceven.bibliotheque.models.utilisateur;

public class Particulier {

    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String codeVille;
    private int caution;

    public Particulier(int id, String nom, String prenom, String adresse, String codeVille, int caution) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.codeVille = codeVille;
        this.caution = caution;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodeVille() {
        return codeVille;
    }

    public void setCodeVille(String codeVille) {
        this.codeVille = codeVille;
    }

    public int getCaution() {
        return caution;
    }

    public void setCaution(int caution) {
        this.caution = caution;
    }
}
