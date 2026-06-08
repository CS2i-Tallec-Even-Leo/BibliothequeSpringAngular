package org.leotalleceven.bibliotheque.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.leotalleceven.bibliotheque.models.lecture.Revue;
import org.leotalleceven.bibliotheque.models.lecture.Stockage;
import org.leotalleceven.bibliotheque.repository.RevueRepository;
import org.leotalleceven.bibliotheque.repository.StockageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RevueControllerTest {

    private RevueRepository revueRepository;
    private StockageRepository stockageRepository;
    private RevueController controller;

    @BeforeEach
    void setUp() {
        revueRepository = mock(RevueRepository.class);
        stockageRepository = mock(StockageRepository.class);
        controller = new RevueController(revueRepository, stockageRepository);
    }

    @Test
    void getAllRevues_shouldReturnList() {
        Revue revue = new Revue();
        revue.setId(1);
        revue.setTitre("Science Aujourd'hui");

        when(revueRepository.findAll()).thenReturn(List.of(revue));

        List<Revue> result = controller.getAllRevues();

        assertThat(result).hasSize(1).contains(revue);
    }

    @Test
    void getRevueById_shouldReturnRevue() {
        Revue revue = new Revue();
        revue.setId(5);
        revue.setTitre("Nature");

        when(revueRepository.findById(5)).thenReturn(Optional.of(revue));

        Revue result = controller.getRevueById(5);

        assertThat(result.getTitre()).isEqualTo("Nature");
    }

    @Test
    void createRevue_shouldResolveStockageAndSave() {
        Stockage payloadStockage = new Stockage();
        payloadStockage.setId(3);

        Revue revue = new Revue();
        revue.setId(99);
        revue.setTitre("National Geographic");
        revue.setStockage(payloadStockage);

        Stockage persistedStockage = new Stockage();
        persistedStockage.setId(3);

        when(stockageRepository.findById(3)).thenReturn(Optional.of(persistedStockage));
        when(revueRepository.save(any(Revue.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Revue saved = controller.createRevue(revue);

        assertThat(saved.getId()).isNull();
        assertThat(saved.getStockage()).isSameAs(persistedStockage);
        verify(revueRepository).save(revue);
    }

    @Test
    void createRevue_shouldFailWhenStockageMissing() {
        Revue revue = new Revue();
        revue.setStockage(null);

        assertThatThrownBy(() -> controller.createRevue(revue))
                .isInstanceOf(ResponseStatusException.class)
                .satisfies(ex -> {
                    ResponseStatusException statusException = (ResponseStatusException) ex;
                    assertThat(statusException.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
                });
    }

    @Test
    void updateRevue_shouldFailWhenStockageDoesNotExist() {
        Stockage payloadStockage = new Stockage();
        payloadStockage.setId(7);

        Revue revue = new Revue();
        revue.setStockage(payloadStockage);

        when(stockageRepository.findById(7)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> controller.updateRevue(10, revue))
                .isInstanceOf(ResponseStatusException.class)
                .satisfies(ex -> {
                    ResponseStatusException statusException = (ResponseStatusException) ex;
                    assertThat(statusException.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
                });
    }

    @Test
    void deleteRevue_shouldCallRepository() {
        controller.deleteRevue(2);

        verify(revueRepository).deleteById(2);
    }
}
