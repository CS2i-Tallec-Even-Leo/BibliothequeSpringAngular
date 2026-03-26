package org.leotalleceven.bibliotheque.models;

import java.util.Map;

public class Ville {

    private final Map<Integer, String> villes;

    public Ville(Map<Integer, String> villes) {
        this.villes = villes;
    }

    public String getVille(int id) {
        return villes.get(id);
    }

    public Integer getCodeVille(String nomVille) {
        for (Map.Entry<Integer, String> entry : villes.entrySet()) {
            if (entry.getValue().equals(nomVille)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
