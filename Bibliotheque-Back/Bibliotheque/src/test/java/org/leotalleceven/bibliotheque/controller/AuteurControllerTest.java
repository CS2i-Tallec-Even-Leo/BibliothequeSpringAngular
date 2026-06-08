package org.leotalleceven.bibliotheque.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.leotalleceven.bibliotheque.models.Auteur;
import org.leotalleceven.bibliotheque.repository.AuteurRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AuteurControllerTest {

    private AuteurRepository auteurRepository;
    private AuteurController controller;

    @BeforeEach
    void setUp() {
        auteurRepository = mock(AuteurRepository.class);
        controller = new AuteurController(auteurRepository);
    }

    @Test
    void getAllAuteurs_shouldReturnEmptyListWhenNoAuthorsExist() {
        when(auteurRepository.findAll()).thenReturn(List.of());

        List<Auteur> result = controller.getAllAuteurs();

        assertThat(result).isEmpty();
        verify(auteurRepository).findAll();
    }

    @Test
    void getAllAuteurs_shouldReturnListOfAuthors() {
        Auteur author1 = new Auteur();
        author1.setId(1);
        author1.setNom("Victor Hugo");

        Auteur author2 = new Auteur();
        author2.setId(2);
        author2.setNom("Balzac");

        when(auteurRepository.findAll()).thenReturn(List.of(author1, author2));

        List<Auteur> result = controller.getAllAuteurs();

        assertThat(result).hasSize(2).contains(author1, author2);
    }

    @Test
    void getAuteurById_shouldReturnAuthorWhenExists() {
        Auteur author = new Auteur();
        author.setId(5);
        author.setNom("Moliere");

        when(auteurRepository.findById(5)).thenReturn(Optional.of(author));

        Auteur result = controller.getAuteurById(5);

        assertThat(result).isEqualTo(author);
    }

    @Test
    void getAuteurById_shouldReturnNullWhenNotExists() {
        when(auteurRepository.findById(999)).thenReturn(Optional.empty());

        Auteur result = controller.getAuteurById(999);

        assertThat(result).isNull();
    }

    @Test
    void createAuteur_shouldSaveAndReturn() {
        Auteur auteur = new Auteur();
        auteur.setNom("Dumas");

        when(auteurRepository.save(any(Auteur.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Auteur result = controller.createAuteur(auteur);

        assertThat(result.getNom()).isEqualTo("Dumas");
        verify(auteurRepository).save(auteur);
    }

    @Test
    void updateAuteur_shouldSetIdAndSave() {
        Auteur auteur = new Auteur();
        auteur.setNom("Flaubert");

        when(auteurRepository.save(any(Auteur.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Auteur result = controller.updateAuteur(10, auteur);

        assertThat(result.getId()).isEqualTo(10);
        assertThat(result.getNom()).isEqualTo("Flaubert");
    }

    @Test
    void deleteAuteur_shouldCallRepository() {
        controller.deleteAuteur(7);

        verify(auteurRepository).deleteById(7);
    }
}
