package org.leotalleceven.bibliotheque.models.lecture;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="stockages")
public class Stockage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int nombreTotal;
    private int restant;
    private int numeroTrave;
    private int numeroEtagere;
    private int niveau;
    private String categorie;

    public Stockage() {
    }

    public Stockage(int nombreTotal, int restant, int numeroTrave, int numeroEtagere, int niveau, String categorie) {
        this.nombreTotal = nombreTotal;
        this.restant = restant;
        this.numeroTrave = numeroTrave;
        this.numeroEtagere = numeroEtagere;
        this.niveau = niveau;
        this.categorie = categorie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNombreTotal() {
        return nombreTotal;
    }

    public void setNombreTotal(int nombreTotal) {
        this.nombreTotal = nombreTotal;
    }

    public int getRestant() {
        return restant;
    }

    public void setRestant(int restant) {
        this.restant = restant;
    }

    public int getNumeroTrave() {
        return numeroTrave;
    }

    public void setNumeroTrave(int numeroTrave) {
        this.numeroTrave = numeroTrave;
    }

    public int getNumeroEtagere() {
        return numeroEtagere;
    }

    public void setNumeroEtagere(int numeroEtagere) {
        this.numeroEtagere = numeroEtagere;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
