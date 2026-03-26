package org.leotalleceven.bibliotheque.models.lecture;

import java.sql.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="revues")
public class Revue extends Ressource {

    private int numeroVolume;
    private Date dateParution;

    public Revue() {
        super();
    }

    public Revue(String titre, int caution, String codeBarre, int numeroVolume, Date dateParution) {
        super(titre, caution, codeBarre);
        this.numeroVolume = numeroVolume;
        this.dateParution = dateParution;
    }

    public int getNumeroVolume() {
        return numeroVolume;
    }

    public void setNumeroVolume(int numeroVolume) {
        this.numeroVolume = numeroVolume;
    }

    public Date getDateParution() {
        return dateParution;
    }

    public void setDateParution(Date dateParution) {
        this.dateParution = dateParution;
    }
}
