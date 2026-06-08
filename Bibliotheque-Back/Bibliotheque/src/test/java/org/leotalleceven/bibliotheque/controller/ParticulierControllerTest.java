package org.leotalleceven.bibliotheque.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.leotalleceven.bibliotheque.models.utilisateur.Particulier;
import org.leotalleceven.bibliotheque.repository.ParticulierRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ParticulierControllerTest {

    private ParticulierRepository particulierRepository;
    private ParticulierController controller;

    @BeforeEach
    void setUp() {
        particulierRepository = mock(ParticulierRepository.class);
        controller = new ParticulierController(particulierRepository);
    }

    @Test
    void getAllParticuliers_shouldReturnList() {
        Particulier user = new Particulier(1, "Martin", "Jean", "123 Rue de Rivoli", "75001", "jean@example.com", 100);

        when(particulierRepository.findAll()).thenReturn(List.of(user));

        List<Particulier> result = controller.getAllParticuliers();

        assertThat(result).hasSize(1).contains(user);
    }

    @Test
    void getParticulierById_shouldReturnParticulier() {
        Particulier user = new Particulier(5, "Dubois", "Marie", "456 Ave Champs", "75008", "marie@example.com", 150);

        when(particulierRepository.findById(5)).thenReturn(Optional.of(user));

        Particulier result = controller.getParticulierById(5);

        assertThat(result.getNom()).isEqualTo("Dubois");
        assertThat(result.getPrenom()).isEqualTo("Marie");
    }

    @Test
    void createParticulier_shouldSave() {
        Particulier user = new Particulier();
        user.setNom("Dupont");
        user.setPrenom("Pierre");
        user.setCaution(80);

        when(particulierRepository.save(any(Particulier.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Particulier result = controller.createParticulier(user);

        assertThat(result.getNom()).isEqualTo("Dupont");
        assertThat(result.getCaution()).isEqualTo(80);
    }

    @Test
    void updateParticulier_shouldSetIdAndSave() {
        Particulier user = new Particulier();
        user.setNom("Lefevre");
        user.setEmail("lefevre@example.com");

        when(particulierRepository.save(any(Particulier.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Particulier result = controller.updateParticulier(10, user);

        assertThat(result.getId()).isEqualTo(10);
        assertThat(result.getNom()).isEqualTo("Lefevre");
    }

    @Test
    void deleteParticulier_shouldCallRepository() {
        controller.deleteParticulier(7);

        verify(particulierRepository).deleteById(7);
    }
}
