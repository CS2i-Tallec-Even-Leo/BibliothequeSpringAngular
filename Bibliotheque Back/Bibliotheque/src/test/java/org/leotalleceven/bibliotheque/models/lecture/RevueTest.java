package org.leotalleceven.bibliotheque.models.lecture;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

class RevueTest {

    @Test
    void revue_shouldBeCreatedWithConstructor() {
        Date date = Date.valueOf("2026-01-01");
        Revue revue = new Revue("Scientific American", 20, "0036-8733", 1, date);

        assertThat(revue.getTitre()).isEqualTo("Scientific American");
        assertThat(revue.getCaution()).isEqualTo(20);
        assertThat(revue.getCodeBarre()).isEqualTo("0036-8733");
        assertThat(revue.getNumeroVolume()).isEqualTo(1);
    }

    @Test
    void revue_shouldBeCreatedEmpty() {
        Revue revue = new Revue();

        assertThat(revue.getId()).isNull();
        assertThat(revue.getTitre()).isNull();
    }

    @Test
    void revue_shouldSupportSetters() {
        Revue revue = new Revue();
        revue.setId(5);
        revue.setTitre("Nature");
        revue.setCaution(25);
        revue.setCodeBarre("0028-0836");
        revue.setNumeroVolume(2);

        assertThat(revue.getId()).isEqualTo(5);
        assertThat(revue.getTitre()).isEqualTo("Nature");
        assertThat(revue.getCaution()).isEqualTo(25);
        assertThat(revue.getNumeroVolume()).isEqualTo(2);
    }
}
