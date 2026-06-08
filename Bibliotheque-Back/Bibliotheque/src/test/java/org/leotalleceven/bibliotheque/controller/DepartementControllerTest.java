package org.leotalleceven.bibliotheque.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.leotalleceven.bibliotheque.models.Departement;
import org.leotalleceven.bibliotheque.repository.DepartementRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DepartementControllerTest {

    private DepartementRepository departementRepository;
    private DepartementController controller;

    @BeforeEach
    void setUp() {
        departementRepository = mock(DepartementRepository.class);
        controller = new DepartementController(departementRepository);
    }

    @Test
    void getAllDepartements_shouldReturnList() {
        Departement dept1 = new Departement();
        dept1.setCodeDepartement(1);
        dept1.setNomDepartement("Informatique");

        when(departementRepository.findAll()).thenReturn(List.of(dept1));

        List<Departement> result = controller.getAllDepartements();

        assertThat(result).hasSize(1).contains(dept1);
    }

    @Test
    void getDepartementById_shouldReturnDepartement() {
        Departement dept = new Departement();
        dept.setCodeDepartement(3);
        dept.setNomDepartement("Mathematiques");

        when(departementRepository.findById(3)).thenReturn(Optional.of(dept));

        Departement result = controller.getDepartementById(3);

        assertThat(result).isEqualTo(dept);
    }

    @Test
    void createDepartement_shouldSave() {
        Departement dept = new Departement();
        dept.setNomDepartement("Physique");

        when(departementRepository.save(any(Departement.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Departement result = controller.createDepartement(dept);

        assertThat(result.getNomDepartement()).isEqualTo("Physique");
    }

    @Test
    void updateDepartement_shouldCallSave() {
        Departement dept = new Departement();
        dept.setNomDepartement("Chimie");

        when(departementRepository.save(any(Departement.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Departement result = controller.updateDepartement(5, dept);

        assertThat(result.getNomDepartement()).isEqualTo("Chimie");
        verify(departementRepository).save(any(Departement.class));
    }

    @Test
    void deleteDepartement_shouldCallRepository() {
        controller.deleteDepartement(2);

        verify(departementRepository).deleteById(2);
    }
}
