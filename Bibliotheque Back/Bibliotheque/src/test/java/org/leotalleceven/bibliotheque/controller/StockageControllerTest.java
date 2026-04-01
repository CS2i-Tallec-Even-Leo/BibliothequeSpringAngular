package org.leotalleceven.bibliotheque.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.leotalleceven.bibliotheque.models.lecture.Stockage;
import org.leotalleceven.bibliotheque.repository.StockageRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StockageControllerTest {

    private StockageRepository stockageRepository;
    private StockageController controller;

    @BeforeEach
    void setUp() {
        stockageRepository = mock(StockageRepository.class);
        controller = new StockageController(stockageRepository);
    }

    @Test
    void getAllStockages_shouldReturnList() {
        Stockage storage1 = new Stockage(10, 5, 1, 1, 1, "Fiction");
        storage1.setId(1);

        when(stockageRepository.findAll()).thenReturn(List.of(storage1));

        List<Stockage> result = controller.getAllStockages();

        assertThat(result).hasSize(1).contains(storage1);
    }

    @Test
    void getStockageById_shouldReturnStockage() {
        Stockage storage = new Stockage(20, 10, 2, 2, 2, "Reference");
        storage.setId(5);

        when(stockageRepository.findById(5)).thenReturn(Optional.of(storage));

        Stockage result = controller.getStockageById(5);

        assertThat(result.getId()).isEqualTo(5);
        assertThat(result.getCategorie()).isEqualTo("Reference");
    }

    @Test
    void createStockage_shouldSave() {
        Stockage storage = new Stockage(15, 8, 1, 3, 1, "Technique");

        when(stockageRepository.save(any(Stockage.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Stockage result = controller.createStockage(storage);

        assertThat(result.getCategorie()).isEqualTo("Technique");
        assertThat(result.getNombreTotal()).isEqualTo(15);
    }

    @Test
    void updateStockage_shouldSave() {
        Stockage storage = new Stockage(25, 12, 3, 4, 2, "Sciences");

        when(stockageRepository.save(any(Stockage.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Stockage result = controller.updateStockage(8, storage);

        assertThat(result.getNombreTotal()).isEqualTo(25);
    }

    @Test
    void deleteStockage_shouldCallRepository() {
        controller.deleteStockage(3);

        verify(stockageRepository).deleteById(3);
    }
}
