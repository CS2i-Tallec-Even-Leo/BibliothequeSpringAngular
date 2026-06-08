package org.leotalleceven.bibliotheque.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.leotalleceven.bibliotheque.models.lecture.Exemplaire;
import org.leotalleceven.bibliotheque.models.lecture.Livre;
import org.leotalleceven.bibliotheque.repository.ExemplaireRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ExemplaireControllerTest {

    private ExemplaireRepository exemplaireRepository;
    private ExemplaireController controller;

    @BeforeEach
    void setUp() {
        exemplaireRepository = mock(ExemplaireRepository.class);
        controller = new ExemplaireController(exemplaireRepository);
    }

    @Test
    void getAllExemplaires_shouldReturnList() {
        Livre livre = new Livre();
        livre.setId(1);

        Exemplaire exemplaire = new Exemplaire("EX001", "Bon", livre, "2025-01-15");
        exemplaire.setId(1);

        when(exemplaireRepository.findAll()).thenReturn(List.of(exemplaire));

        List<Exemplaire> result = controller.getAllExemplaires();

        assertThat(result).hasSize(1).contains(exemplaire);
    }

    @Test
    void getExemplaireById_shouldReturnExemplaire() {
        Livre livre = new Livre();
        livre.setId(2);

        Exemplaire exemplaire = new Exemplaire("EX002", "Usé", livre, "2024-06-10");
        exemplaire.setId(10);

        when(exemplaireRepository.findById(10)).thenReturn(Optional.of(exemplaire));

        Exemplaire result = controller.getExemplaireById(10);

        assertThat(result.getNumeroExemplaire()).isEqualTo("EX002");
        assertThat(result.getEtat()).isEqualTo("Usé");
    }

    @Test
    void createExemplaire_shouldSave() {
        Livre livre = new Livre();
        livre.setId(3);

        Exemplaire exemplaire = new Exemplaire("EX003", "Neuf", livre, "2026-01-01");

        when(exemplaireRepository.save(any(Exemplaire.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Exemplaire result = controller.createExemplaire(exemplaire);

        assertThat(result.getNumeroExemplaire()).isEqualTo("EX003");
        assertThat(result.getEtat()).isEqualTo("Neuf");
    }

    @Test
    void updateExemplaire_shouldSetIdAndSave() {
        Livre livre = new Livre();
        livre.setId(4);

        Exemplaire exemplaire = new Exemplaire("EX004", "Bon", livre, "2025-03-20");

        when(exemplaireRepository.save(any(Exemplaire.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Exemplaire result = controller.updateExemplaire(15, exemplaire);

        assertThat(result.getId()).isEqualTo(15);
        assertThat(result.getEtat()).isEqualTo("Bon");
    }

    @Test
    void deleteExemplaire_shouldCallRepository() {
        controller.deleteExemplaire(5);

        verify(exemplaireRepository).deleteById(5);
    }
}
