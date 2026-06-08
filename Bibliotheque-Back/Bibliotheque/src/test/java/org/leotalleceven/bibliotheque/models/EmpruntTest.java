package org.leotalleceven.bibliotheque.models;

import org.junit.jupiter.api.Test;
import org.leotalleceven.bibliotheque.models.lecture.Exemplaire;
import org.leotalleceven.bibliotheque.models.utilisateur.Particulier;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class EmpruntTest {

    @Test
    void emprunt_shouldBeCreatedWithConstructor() {
        Emprunt emprunt = new Emprunt(LocalDate.of(2026, 4, 1), LocalDate.of(2026, 4, 15), "EN_COURS");

        assertThat(emprunt.getDateEmprunt()).isEqualTo(LocalDate.of(2026, 4, 1));
        assertThat(emprunt.getDateRetourPrevue()).isEqualTo(LocalDate.of(2026, 4, 15));
        assertThat(emprunt.getStatut()).isEqualTo("EN_COURS");
        assertThat(emprunt.getDateRetourEffectif()).isNull();
    }

    @Test
    void emprunt_shouldBeCreatedEmpty() {
        Emprunt emprunt = new Emprunt();

        assertThat(emprunt.getId()).isNull();
        assertThat(emprunt.getStatut()).isNull();
        assertThat(emprunt.getDateEmprunt()).isNull();
        assertThat(emprunt.getDateRetourPrevue()).isNull();
        assertThat(emprunt.getDateRetourEffectif()).isNull();
        assertThat(emprunt.getExemplaire()).isNull();
        assertThat(emprunt.getParticulier()).isNull();
    }

    @Test
    void emprunt_shouldSupportSetters() {
        Particulier utilisateur = new Particulier();
        utilisateur.setId(1);

        Exemplaire exemplaire = new Exemplaire();
        exemplaire.setId(10);

        Emprunt emprunt = new Emprunt();
        emprunt.setId(50);
        emprunt.setStatut("RETOURNE");
        emprunt.setDateEmprunt(LocalDate.of(2026, 3, 15));
        emprunt.setDateRetourPrevue(LocalDate.of(2026, 3, 30));
        emprunt.setDateRetourEffectif(LocalDate.of(2026, 3, 28));
        emprunt.setParticulier(utilisateur);
        emprunt.setExemplaire(exemplaire);

        assertThat(emprunt.getId()).isEqualTo(50);
        assertThat(emprunt.getStatut()).isEqualTo("RETOURNE");
        assertThat(emprunt.getDateEmprunt()).isEqualTo(LocalDate.of(2026, 3, 15));
        assertThat(emprunt.getDateRetourPrevue()).isEqualTo(LocalDate.of(2026, 3, 30));
        assertThat(emprunt.getDateRetourEffectif()).isEqualTo(LocalDate.of(2026, 3, 28));
        assertThat(emprunt.getParticulier()).isEqualTo(utilisateur);
        assertThat(emprunt.getExemplaire()).isEqualTo(exemplaire);
    }
}
