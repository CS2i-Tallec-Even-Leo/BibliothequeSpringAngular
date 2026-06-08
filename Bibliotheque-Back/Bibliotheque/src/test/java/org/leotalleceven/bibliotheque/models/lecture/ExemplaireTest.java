package org.leotalleceven.bibliotheque.models.lecture;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ExemplaireTest {

    @Test
    void exemplaire_shouldBeCreatedWithConstructor() {
        Livre livre = new Livre();
        livre.setId(1);

        Exemplaire exemplaire = new Exemplaire("EX001", "Bon", livre, "2025-01-15");

        assertThat(exemplaire.getNumeroExemplaire()).isEqualTo("EX001");
        assertThat(exemplaire.getEtat()).isEqualTo("Bon");
        assertThat(exemplaire.getRessource()).isEqualTo(livre);
        assertThat(exemplaire.getDateAcquisition()).isEqualTo("2025-01-15");
    }

    @Test
    void exemplaire_shouldBeCreatedEmpty() {
        Exemplaire exemplaire = new Exemplaire();

        assertThat(exemplaire.getId()).isNull();
        assertThat(exemplaire.getNumeroExemplaire()).isNull();
        assertThat(exemplaire.getDateAcquisition()).isNull();

    }

    @Test
    void exemplaire_shouldSupportSetters() {
        Livre livre = new Livre();
        livre.setId(2);

        Exemplaire exemplaire = new Exemplaire();
        exemplaire.setId(10);
        exemplaire.setNumeroExemplaire("EX010");
        exemplaire.setEtat("Usé");
        exemplaire.setRessource(livre);
        exemplaire.setDateAcquisition("2024-06-01");

        assertThat(exemplaire.getId()).isEqualTo(10);
        assertThat(exemplaire.getNumeroExemplaire()).isEqualTo("EX010");
        assertThat(exemplaire.getEtat()).isEqualTo("Usé");
        assertThat(exemplaire.getRessource()).isEqualTo(livre);
        assertThat(exemplaire.getDateAcquisition()).isEqualTo("2024-06-01");
    }
}
