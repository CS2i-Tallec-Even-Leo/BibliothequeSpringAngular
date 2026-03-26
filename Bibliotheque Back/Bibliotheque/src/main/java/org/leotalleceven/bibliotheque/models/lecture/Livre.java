package org.leotalleceven.bibliotheque.models.lecture;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="Livres")
public class Livre extends Ressource {

    private String iSBN;
    private String Auteur;
    private String Genre;

    public Livre() {
        super();
    }

    public Livre(String titre, int caution, String codeBarre, String iSBN, String auteur, String genre) {
        super(titre, caution, codeBarre);
        this.iSBN = iSBN;
        Auteur = auteur;
        Genre = genre;
    }

    public String getiSBN() {
        return iSBN;
    }

    public void setiSBN(String iSBN) {
        this.iSBN = iSBN;
    }

    public String getAuteur() {
        return Auteur;
    }

    public void setAuteur(String auteur) {
        Auteur = auteur;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }
}
