package org.leotalleceven.bibliotheque.models.utilisateur;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EtudiantTest {

    @Test
    void etudiant_shouldBeCreatedWithConstructor() {
        Etudiant student = new Etudiant(4, "Petit", "Anne", "222 Rue", "75012", "anne@etu.fr", 60, 2024);

        assertThat(student.getId()).isEqualTo(4);
        assertThat(student.getNom()).isEqualTo("Petit");
        assertThat(student.getAnneeUniversitaire()).isEqualTo(2024);
    }

    @Test
    void etudiant_hasInheritedParticulierProperties() {
        Etudiant student = new Etudiant();
        student.setId(2);
        student.setNom("Roux");
        student.setCaution(75);
        student.setAnneeUniversitaire(2025);

        assertThat(student.getId()).isEqualTo(2);
        assertThat(student.getNom()).isEqualTo("Roux");
        assertThat(student.getCaution()).isEqualTo(75);
        assertThat(student.getAnneeUniversitaire()).isEqualTo(2025);
    }
}
