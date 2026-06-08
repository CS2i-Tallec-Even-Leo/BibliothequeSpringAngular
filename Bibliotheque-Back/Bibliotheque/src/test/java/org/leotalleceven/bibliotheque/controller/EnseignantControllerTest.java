package org.leotalleceven.bibliotheque.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.leotalleceven.bibliotheque.models.utilisateur.Enseignant;
import org.leotalleceven.bibliotheque.repository.EnseignantRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EnseignantControllerTest {

    private EnseignantRepository enseignantRepository;
    private EnseignantController controller;

    @BeforeEach
    void setUp() {
        enseignantRepository = mock(EnseignantRepository.class);
        controller = new EnseignantController(enseignantRepository);
    }

    @Test
    void getAllEnseignants_shouldReturnList() {
        Enseignant teacher = new Enseignant(1, "Leclerc", "Claude", "789 Boulevard", "75010", "claude@univ.fr", 200, 1);

        when(enseignantRepository.findAll()).thenReturn(List.of(teacher));

        List<Enseignant> result = controller.getAllEnseignants();

        assertThat(result).hasSize(1).contains(teacher);
    }

    @Test
    void getEnseignantById_shouldReturnEnseignant() {
        Enseignant teacher = new Enseignant(3, "Bernard", "Sophie", "321 Chaussee", "75011", "sophie@univ.fr", 250, 2);

        when(enseignantRepository.findById(3)).thenReturn(Optional.of(teacher));

        Enseignant result = controller.getEnseignantById(3);

        assertThat(result.getNom()).isEqualTo("Bernard");
        assertThat(result.getCodeDepartement()).isEqualTo(2);
    }

    @Test
    void createEnseignant_shouldSave() {
        Enseignant teacher = new Enseignant();
        teacher.setNom("Renard");
        teacher.setPrenom("Francoise");
        teacher.setCodeDepartement(3);

        when(enseignantRepository.save(any(Enseignant.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Enseignant result = controller.createEnseignant(teacher);

        assertThat(result.getNom()).isEqualTo("Renard");
        assertThat(result.getCodeDepartement()).isEqualTo(3);
    }

    @Test
    void updateEnseignant_shouldSetIdAndSave() {
        Enseignant teacher = new Enseignant();
        teacher.setNom("Blanc");
        teacher.setCodeDepartement(4);

        when(enseignantRepository.save(any(Enseignant.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Enseignant result = controller.updateEnseignant(8, teacher);

        assertThat(result.getId()).isEqualTo(8);
        assertThat(result.getCodeDepartement()).isEqualTo(4);
    }

    @Test
    void deleteEnseignant_shouldCallRepository() {
        controller.deleteEnseignant(6);

        verify(enseignantRepository).deleteById(6);
    }
}
