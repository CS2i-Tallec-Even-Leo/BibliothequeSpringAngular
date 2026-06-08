package org.leotalleceven.bibliotheque.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AuteurTest {

    @Test
    void auteur_shouldBeCreatedWithConstructor() {
        Auteur auteur = new Auteur(1, "Dumas", "Alexandre");

        assertThat(auteur.getId()).isEqualTo(1);
        assertThat(auteur.getNom()).isEqualTo("Dumas");
        assertThat(auteur.getPrenom()).isEqualTo("Alexandre");
    }

    @Test
    void auteur_shouldBeCreatedEmpty() {
        Auteur auteur = new Auteur();

        assertThat(auteur.getId()).isNull();
        assertThat(auteur.getNom()).isNull();
        assertThat(auteur.getPrenom()).isNull();
    }

    @Test
    void auteur_shouldSupportSettersAndGetters() {
        Auteur auteur = new Auteur();
        auteur.setId(42);
        auteur.setNom("Hugo");
        auteur.setPrenom("Victor");

        assertThat(auteur.getId()).isEqualTo(42);
        assertThat(auteur.getNom()).isEqualTo("Hugo");
        assertThat(auteur.getPrenom()).isEqualTo("Victor");
    }
}
