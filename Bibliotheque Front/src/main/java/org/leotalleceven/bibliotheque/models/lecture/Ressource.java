package org.leotalleceven.bibliotheque.models.lecture;

public abstract class Ressource {

    private String titre;
    private int caution;
    private String codeBarre;

    public Ressource(String titre, int caution, String codeBarre) {
        this.titre = titre;
        this.caution = caution;
        this.codeBarre = codeBarre;
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
}
