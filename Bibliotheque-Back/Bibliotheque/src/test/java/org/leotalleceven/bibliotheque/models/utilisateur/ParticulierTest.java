package org.leotalleceven.bibliotheque.models.utilisateur;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParticulierTest {

    @Test
    void particulier_shouldBeCreatedWithConstructor() {
        Particulier user = new Particulier(
            1,
            "Dupont",
            "Jean",
            "123 Rue",
            "75001",
            "jean@example.com",
            100
        );

        assertThat(user.getId()).isEqualTo(1);
        assertThat(user.getNom()).isEqualTo("Dupont");
        assertThat(user.getPrenom()).isEqualTo("Jean");
        assertThat(user.getAdresse()).isEqualTo("123 Rue");
        assertThat(user.getCodeVille()).isEqualTo("75001");
        assertThat(user.getEmail()).isEqualTo("jean@example.com");
        assertThat(user.getCaution()).isEqualTo(100);
    }

    @Test
    void particulier_shouldBeCreatedEmpty() {
        Particulier user = new Particulier();

        assertThat(user.getId()).isEqualTo(0);
        assertThat(user.getNom()).isNull();
        assertThat(user.getPrenom()).isNull();
        assertThat(user.getAdresse()).isNull();
        assertThat(user.getCodeVille()).isNull();
        assertThat(user.getEmail()).isNull();
        assertThat(user.getCaution()).isEqualTo(0);
    }

    @Test
    void particulier_shouldSupportSetters() {
        Particulier user = new Particulier();
        user.setId(5);
        user.setNom("Martin");
        user.setPrenom("Marie");
        user.setAdresse("456 Avenue");
        user.setCodeVille("75002");
        user.setCaution(150);
        user.setEmail("marie@example.com");

        assertThat(user.getId()).isEqualTo(5);
        assertThat(user.getNom()).isEqualTo("Martin");
        assertThat(user.getPrenom()).isEqualTo("Marie");
        assertThat(user.getAdresse()).isEqualTo("456 Avenue");
        assertThat(user.getCodeVille()).isEqualTo("75002");
        assertThat(user.getCaution()).isEqualTo(150);
        assertThat(user.getEmail()).isEqualTo("marie@example.com");
    }
}
