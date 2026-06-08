package org.leotalleceven.bibliotheque.models.lecture;

import jakarta.persistence.*;

@Entity
@Table(name = "exemplaires")
public class Exemplaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numero_exemplaire")
    private String numeroExemplaire;

    private String etat;

    @ManyToOne
    @JoinColumn(name = "ressource_id")
    private Ressource ressource;

    @Column(name = "date_acquisition")
    private String dateAcquisition;

    public Exemplaire() {
    }

    public Exemplaire(String numeroExemplaire, String etat, Ressource ressource, String dateAcquisition) {
        this.numeroExemplaire = numeroExemplaire;
        this.etat = etat;
        this.ressource = ressource;
        this.dateAcquisition = dateAcquisition;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroExemplaire() {
        return numeroExemplaire;
    }

    public void setNumeroExemplaire(String numeroExemplaire) {
        this.numeroExemplaire = numeroExemplaire;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Ressource getRessource() {
        return ressource;
    }

    public void setRessource(Ressource ressource) {
        this.ressource = ressource;
    }

    public String getDateAcquisition() {
        return dateAcquisition;
    }

    public void setDateAcquisition(String dateAcquisition) {
        this.dateAcquisition = dateAcquisition;
    }
}
