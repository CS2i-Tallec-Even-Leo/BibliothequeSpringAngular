package org.leotalleceven.bibliotheque.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "emprunts")
public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date_emprunt")
    private LocalDate dateEmprunt;

    @Column(name = "date_retour_prevue")
    private LocalDate dateRetourPrevue;

    @Column(name = "date_retour_effectif")
    private LocalDate dateRetourEffectif;

    private String statut;

    @ManyToOne
    @JoinColumn(name = "particulier_id")
    private org.leotalleceven.bibliotheque.models.utilisateur.Particulier particulier;

    @ManyToOne
    @JoinColumn(name = "exemplaire_id")
    private org.leotalleceven.bibliotheque.models.lecture.Exemplaire exemplaire;

    public Emprunt() {
    }

    public Emprunt(LocalDate dateEmprunt, LocalDate dateRetourPrevue, String statut) {
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        this.statut = statut;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDate getDateRetourPrevue() {
        return dateRetourPrevue;
    }

    public void setDateRetourPrevue(LocalDate dateRetourPrevue) {
        this.dateRetourPrevue = dateRetourPrevue;
    }

    public LocalDate getDateRetourEffectif() {
        return dateRetourEffectif;
    }

    public void setDateRetourEffectif(LocalDate dateRetourEffectif) {
        this.dateRetourEffectif = dateRetourEffectif;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public org.leotalleceven.bibliotheque.models.utilisateur.Particulier getParticulier() {
        return particulier;
    }

    public void setParticulier(org.leotalleceven.bibliotheque.models.utilisateur.Particulier particulier) {
        this.particulier = particulier;
    }

    public org.leotalleceven.bibliotheque.models.lecture.Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(org.leotalleceven.bibliotheque.models.lecture.Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }
}
