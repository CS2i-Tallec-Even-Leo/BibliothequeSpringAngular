package org.leotalleceven.bibliotheque.models.lecture;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LivreTest {

    @Test
    void livre_shouldBeCreatedWithConstructor() {
        Livre livre = new Livre("1984", 50, "CODE-1984-001", "978-0451524935", "George Orwell", "Dystopie", 1949);

        assertThat(livre.getTitre()).isEqualTo("1984");
        assertThat(livre.getCaution()).isEqualTo(50);
        assertThat(livre.getCodeBarre()).isEqualTo("CODE-1984-001");
        assertThat(livre.getiSBN()).isEqualTo("978-0451524935");
        assertThat(livre.getAuteur()).isEqualTo("George Orwell");
        assertThat(livre.getGenre()).isEqualTo("Dystopie");
        assertThat(livre.getAnneePublication()).isEqualTo(1949);
    }

    @Test
    void livre_shouldBeCreatedEmpty() {
        Livre livre = new Livre();

        assertThat(livre.getId()).isNull();
        assertThat(livre.getTitre()).isNull();
        assertThat(livre.getCodeBarre()).isNull();
        assertThat(livre.getiSBN()).isNull();
        assertThat(livre.getAuteur()).isNull();
        assertThat(livre.getGenre()).isNull();
        assertThat(livre.getAnneePublication()).isNull();
        assertThat(livre.getCaution()).isEqualTo(0);
    }

    @Test
    void livre_shouldSupportSetters() {
        Livre livre = new Livre();
        livre.setId(10);
        livre.setTitre("Le Seigneur des Anneaux");
        livre.setCodeBarre("123456789");
        livre.setiSBN("978-0544003415");
        livre.setAuteur("Tolkien");
        livre.setGenre("Fantasy");
        livre.setAnneePublication(1954);
        livre.setCaution(60);

        assertThat(livre.getId()).isEqualTo(10);
        assertThat(livre.getTitre()).isEqualTo("Le Seigneur des Anneaux");
        assertThat(livre.getCodeBarre()).isEqualTo("123456789");
        assertThat(livre.getiSBN()).isEqualTo("978-0544003415");
        assertThat(livre.getAuteur()).isEqualTo("Tolkien");
        assertThat(livre.getGenre()).isEqualTo("Fantasy");
        assertThat(livre.getAnneePublication()).isEqualTo(1954);
        assertThat(livre.getCaution()).isEqualTo(60);
    }
}
