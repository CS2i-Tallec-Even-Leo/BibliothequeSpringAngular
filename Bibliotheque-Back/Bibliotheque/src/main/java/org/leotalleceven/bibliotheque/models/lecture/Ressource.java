package org.leotalleceven.bibliotheque.models.lecture;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="ressources")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Ressource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titre;
    private int caution;
    private String codeBarre;

    @ManyToOne
    @JoinColumn(name = "stockage_id")
    private Stockage stockage;

    public Ressource() {
    }

    public Ressource(String titre, int caution, String codeBarre) {
        this.titre = titre;
        this.caution = caution;
        this.codeBarre = codeBarre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getCaution() {
        return caution;
    }

    public void setCaution(int caution) {
        this.caution = caution;
    }

    public String getCodeBarre() {
        return codeBarre;
    }

    public void setCodeBarre(String codeBarre) {
        this.codeBarre = codeBarre;
    }

    public Stockage getStockage() {
        return stockage;
    }

    public void setStockage(Stockage stockage) {
        this.stockage = stockage;
    }
}
