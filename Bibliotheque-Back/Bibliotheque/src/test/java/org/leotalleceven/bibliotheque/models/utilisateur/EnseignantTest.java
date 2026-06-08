package org.leotalleceven.bibliotheque.models.utilisateur;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EnseignantTest {

    @Test
    void enseignant_shouldBeCreatedWithConstructor() {
        Enseignant teacher = new Enseignant(3, "Leclerc", "Claude", "789 Blvd", "75010", "claude@univ.fr", 200, 5);

        assertThat(teacher.getId()).isEqualTo(3);
        assertThat(teacher.getNom()).isEqualTo("Leclerc");
        assertThat(teacher.getPrenom()).isEqualTo("Claude");
        assertThat(teacher.getAdresse()).isEqualTo("789 Blvd");
        assertThat(teacher.getCodeVille()).isEqualTo("75010");
        assertThat(teacher.getEmail()).isEqualTo("claude@univ.fr");
        assertThat(teacher.getCaution()).isEqualTo(200);
        assertThat(teacher.getCodeDepartement()).isEqualTo(5);
    }

    @Test
    void enseignant_hasInheritedParticulierProperties() {
        Enseignant teacher = new Enseignant();
        teacher.setId(1);
        teacher.setNom("Bernard");
        teacher.setPrenom("Jean");
        teacher.setAdresse("123 Rue");
        teacher.setCodeVille("75001");
        teacher.setEmail("bernard@univ.fr");
        teacher.setCaution(150);
        teacher.setCodeDepartement(2);

        assertThat(teacher.getId()).isEqualTo(1);
        assertThat(teacher.getNom()).isEqualTo("Bernard");
        assertThat(teacher.getPrenom()).isEqualTo("Jean");
        assertThat(teacher.getAdresse()).isEqualTo("123 Rue");
        assertThat(teacher.getCodeVille()).isEqualTo("75001");
        assertThat(teacher.getEmail()).isEqualTo("bernard@univ.fr");
        assertThat(teacher.getCaution()).isEqualTo(150);
        assertThat(teacher.getCodeDepartement()).isEqualTo(2);
    }
}
