package org.leotalleceven.bibliotheque.models.utilisateur;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParticulierTest {

    @Test
    void particulier_shouldBeCreatedWithConstructor() {
        Particulier user = new Particulier(1, "Dupont", "Jean", "123 Rue", "75001", "jean@example.com", 100);

        assertThat(user.getId()).isEqualTo(1);
        assertThat(user.getNom()).isEqualTo("Dupont");
        assertThat(user.getPrenom()).isEqualTo("Jean");
        assertThat(user.getCaution()).isEqualTo(100);
        assertThat(user.getEmail()).isEqualTo("jean@example.com");
    }

    @Test
    void particulier_shouldBeCreatedEmpty() {
        Particulier user = new Particulier();

        assertThat(user.getId()).isEqualTo(0);
        assertThat(user.getNom()).isNull();
    }

    @Test
    void particulier_shouldSupportSetters() {
        Particulier user = new Particulier();
        user.setId(5);
        user.setNom("Martin");
        user.setPrenom("Marie");
        user.setCaution(150);
        user.setEmail("marie@example.com");

        assertThat(user.getId()).isEqualTo(5);
        assertThat(user.getNom()).isEqualTo("Martin");
        assertThat(user.getCaution()).isEqualTo(150);
    }
}
