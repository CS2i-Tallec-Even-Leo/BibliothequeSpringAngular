package org.leotalleceven.bibliotheque.models.lecture;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StockageTest {

    @Test
    void stockage_shouldBeCreatedWithConstructor() {
        Stockage stockage = new Stockage(100, 80, 1, 2, 3, "Fiction");

        assertThat(stockage.getNombreTotal()).isEqualTo(100);
        assertThat(stockage.getRestant()).isEqualTo(80);
        assertThat(stockage.getNumeroTrave()).isEqualTo(1);
        assertThat(stockage.getNumeroEtagere()).isEqualTo(2);
        assertThat(stockage.getNiveau()).isEqualTo(3);
        assertThat(stockage.getCategorie()).isEqualTo("Fiction");
    }

    @Test
    void stockage_shouldBeCreatedEmpty() {
        Stockage stockage = new Stockage();

        assertThat(stockage.getId()).isEqualTo(0);
        assertThat(stockage.getCategorie()).isNull();
        assertThat(stockage.getNombreTotal()).isEqualTo(0);
        assertThat(stockage.getRestant()).isEqualTo(0);
        assertThat(stockage.getNumeroTrave()).isEqualTo(0);
        assertThat(stockage.getNumeroEtagere()).isEqualTo(0);
        assertThat(stockage.getNiveau()).isEqualTo(0);

    }

    @Test
    void stockage_shouldSupportSetters() {
        Stockage stockage = new Stockage();
        stockage.setId(5);
        stockage.setNombreTotal(50);
        stockage.setRestant(30);
        stockage.setNumeroTrave(2);
        stockage.setNumeroEtagere(1);
        stockage.setNiveau(2);
        stockage.setCategorie("Reference");

        assertThat(stockage.getId()).isEqualTo(5);
        assertThat(stockage.getNombreTotal()).isEqualTo(50);
        assertThat(stockage.getRestant()).isEqualTo(30);
        assertThat(stockage.getNumeroTrave()).isEqualTo(2);
        assertThat(stockage.getNumeroEtagere()).isEqualTo(1);
        assertThat(stockage.getNiveau()).isEqualTo(2);
        assertThat(stockage.getCategorie()).isEqualTo("Reference");
    }
}
