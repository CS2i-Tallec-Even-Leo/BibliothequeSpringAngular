package org.leotalleceven.bibliotheque.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.leotalleceven.bibliotheque.models.utilisateur.Etudiant;
import org.leotalleceven.bibliotheque.repository.EtudiantRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EtudiantControllerTest {

    private EtudiantRepository etudiantRepository;
    private EtudiantController controller;

    @BeforeEach
    void setUp() {
        etudiantRepository = mock(EtudiantRepository.class);
        controller = new EtudiantController(etudiantRepository);
    }

    @Test
    void getAllEtudiants_shouldReturnList() {
        Etudiant student = new Etudiant(1, "Moreau", "Luc", "111 Place", "75012", "luc@etu.fr", 50, 2024);

        when(etudiantRepository.findAll()).thenReturn(List.of(student));

        List<Etudiant> result = controller.getAllEtudiants();

        assertThat(result).hasSize(1).contains(student);
    }

    @Test
    void getEtudiantById_shouldReturnEtudiant() {
        Etudiant student = new Etudiant(4, "Petit", "Anne", "222 Impasse", "75013", "anne@etu.fr", 60, 2023);

        when(etudiantRepository.findById(4)).thenReturn(Optional.of(student));

        Etudiant result = controller.getEtudiantById(4);

        assertThat(result.getNom()).isEqualTo("Petit");
        assertThat(result.getAnneeUniversitaire()).isEqualTo(2023);
    }

    @Test
    void createEtudiant_shouldSave() {
        Etudiant student = new Etudiant();
        student.setNom("Roux");
        student.setPrenom("Jules");
        student.setAnneeUniversitaire(2025);

        when(etudiantRepository.save(any(Etudiant.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Etudiant result = controller.createEtudiant(student);

        assertThat(result.getNom()).isEqualTo("Roux");
        assertThat(result.getAnneeUniversitaire()).isEqualTo(2025);
    }

    @Test
    void updateEtudiant_shouldSetIdAndSave() {
        Etudiant student = new Etudiant();
        student.setNom("Girard");
        student.setAnneeUniversitaire(2024);

        when(etudiantRepository.save(any(Etudiant.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Etudiant result = controller.updateEtudiant(9, student);

        assertThat(result.getId()).isEqualTo(9);
        assertThat(result.getAnneeUniversitaire()).isEqualTo(2024);
    }

    @Test
    void deleteEtudiant_shouldCallRepository() {
        controller.deleteEtudiant(11);

        verify(etudiantRepository).deleteById(11);
    }
}
