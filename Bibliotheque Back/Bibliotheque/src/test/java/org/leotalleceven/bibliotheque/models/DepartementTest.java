package org.leotalleceven.bibliotheque.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DepartementTest {

    @Test
    void departement_shouldBeCreatedWithConstructor() {
        Departement dept = new Departement(1, "Informatique");

        assertThat(dept.getCodeDepartement()).isEqualTo(1);
        assertThat(dept.getNomDepartement()).isEqualTo("Informatique");
    }

    @Test
    void departement_shouldBeCreatedEmpty() {
        Departement dept = new Departement();

        assertThat(dept.getNomDepartement()).isNull();
    }

    @Test
    void departement_shouldSupportSetters() {
        Departement dept = new Departement();
        dept.setCodeDepartement(2);
        dept.setNomDepartement("Mathematiques");

        assertThat(dept.getCodeDepartement()).isEqualTo(2);
        assertThat(dept.getNomDepartement()).isEqualTo("Mathematiques");
    }
}

