package org.leotalleceven.bibliotheque.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.leotalleceven.bibliotheque.models.Ville;
import org.leotalleceven.bibliotheque.repository.VilleRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class VilleControllerTest {

    private VilleRepository villeRepository;
    private VilleController controller;

    @BeforeEach
    void setUp() {
        villeRepository = mock(VilleRepository.class);
        controller = new VilleController(villeRepository);
    }

    @Test
    void getAllVilles_shouldReturnList() {
        Ville city = new Ville();
        city.setCodeVille(75001);
        city.setNomVille("Paris");

        when(villeRepository.findAll()).thenReturn(List.of(city));

        List<Ville> result = controller.getAllVilles();

        assertThat(result).hasSize(1).contains(city);
    }

    @Test
    void getVilleById_shouldReturnVille() {
        Ville city = new Ville();
        city.setCodeVille(69001);
        city.setNomVille("Lyon");

        when(villeRepository.findById(2)).thenReturn(Optional.of(city));

        Ville result = controller.getVilleById(2);

        assertThat(result.getNomVille()).isEqualTo("Lyon");
    }

    @Test
    void createVille_shouldSave() {
        Ville city = new Ville();
        city.setNomVille("Marseille");

        when(villeRepository.save(any(Ville.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Ville result = controller.createVille(city);

        assertThat(result.getNomVille()).isEqualTo("Marseille");
    }

    @Test
    void updateVille_shouldSave() {
        Ville city = new Ville();
        city.setNomVille("Toulouse");

        when(villeRepository.save(any(Ville.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Ville result = controller.updateVille(3, city);

        assertThat(result.getNomVille()).isEqualTo("Toulouse");
        verify(villeRepository).save(any(Ville.class));
    }

    @Test
    void deleteVille_shouldCallRepository() {
        controller.deleteVille(4);

        verify(villeRepository).deleteById(4);
    }
}
