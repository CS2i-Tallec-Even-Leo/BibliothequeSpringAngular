package org.leotalleceven.bibliotheque.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class VilleTest {

    @Test
    void ville_shouldBeCreatedWithConstructor() {
        Ville ville = new Ville(75001, "Paris");

        assertThat(ville.getCodeVille()).isEqualTo(75001);
        assertThat(ville.getNomVille()).isEqualTo("Paris");
    }

    @Test
    void ville_shouldBeCreatedEmpty() {
        Ville ville = new Ville();

        assertThat(ville.getCodeVille()).isEqualTo(0);
        assertThat(ville.getNomVille()).isNull();
    }

    @Test
    void ville_shouldSupportSetters() {
        Ville ville = new Ville();
        ville.setCodeVille(69001);
        ville.setNomVille("Lyon");

        assertThat(ville.getCodeVille()).isEqualTo(69001);
        assertThat(ville.getNomVille()).isEqualTo("Lyon");
    }
}
