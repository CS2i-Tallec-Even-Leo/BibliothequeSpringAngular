package org.leotalleceven.bibliotheque.models.utilisateur;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EnseignantTest {

    @Test
    void enseignant_shouldBeCreatedWithConstructor() {
        Enseignant teacher = new Enseignant(3, "Leclerc", "Claude", "789 Blvd", "75010", "claude@univ.fr", 200, 5);

        assertThat(teacher.getId()).isEqualTo(3);
        assertThat(teacher.getNom()).isEqualTo("Leclerc");
        assertThat(teacher.getCodeDepartement()).isEqualTo(5);
    }

    @Test
    void enseignant_hasInheritedParticulierProperties() {
        Enseignant teacher = new Enseignant();
        teacher.setId(1);
        teacher.setNom("Bernard");
        teacher.setEmail("bernard@univ.fr");
        teacher.setCodeDepartement(2);

        assertThat(teacher.getId()).isEqualTo(1);
        assertThat(teacher.getNom()).isEqualTo("Bernard");
        assertThat(teacher.getEmail()).isEqualTo("bernard@univ.fr");
        assertThat(teacher.getCodeDepartement()).isEqualTo(2);
    }
}
