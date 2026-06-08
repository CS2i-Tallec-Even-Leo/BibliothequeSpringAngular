package org.leotalleceven.bibliotheque.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.leotalleceven.bibliotheque.models.lecture.Livre;
import org.leotalleceven.bibliotheque.models.lecture.Stockage;
import org.leotalleceven.bibliotheque.repository.LivreRepository;
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

class LivreControllerTest {

    private LivreRepository livreRepository;
    private StockageRepository stockageRepository;
    private LivreController controller;

    @BeforeEach
    void setUp() {
        livreRepository = mock(LivreRepository.class);
        stockageRepository = mock(StockageRepository.class);
        controller = new LivreController(livreRepository, stockageRepository);
    }

    @Test
    void searchLivres_shouldMapBlankParamsToNull() {
        when(livreRepository.searchLivres(null, null, 2024, null)).thenReturn(List.of());

        List<Livre> result = controller.searchLivres(" ", "", 2024, "   ");

        assertThat(result).isEmpty();
        verify(livreRepository).searchLivres(null, null, 2024, null);
    }

    @Test
    void createLivre_shouldResolveStockageAndSave() {
        Stockage payloadStockage = new Stockage();
        payloadStockage.setId(3);

        Livre livre = new Livre();
        livre.setId(99);
        livre.setStockage(payloadStockage);

        Stockage persistedStockage = new Stockage();
        persistedStockage.setId(3);

        when(stockageRepository.findById(3)).thenReturn(Optional.of(persistedStockage));
        when(livreRepository.save(any(Livre.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Livre saved = controller.createLivre(livre);

        assertThat(saved.getId()).isNull();
        assertThat(saved.getStockage()).isSameAs(persistedStockage);
        verify(livreRepository).save(livre);
    }

    @Test
    void createLivre_shouldFailWhenStockageMissing() {
        Livre livre = new Livre();
        livre.setStockage(null);

        assertThatThrownBy(() -> controller.createLivre(livre))
                .isInstanceOf(ResponseStatusException.class)
                .satisfies(ex -> {
                    ResponseStatusException statusException = (ResponseStatusException) ex;
                    assertThat(statusException.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
                });
    }

    @Test
    void updateLivre_shouldFailWhenStockageDoesNotExist() {
        Stockage payloadStockage = new Stockage();
        payloadStockage.setId(7);

        Livre livre = new Livre();
        livre.setStockage(payloadStockage);

        when(stockageRepository.findById(7)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> controller.updateLivre(10, livre))
                .isInstanceOf(ResponseStatusException.class)
                .satisfies(ex -> {
                    ResponseStatusException statusException = (ResponseStatusException) ex;
                    assertThat(statusException.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
                });
    }
}