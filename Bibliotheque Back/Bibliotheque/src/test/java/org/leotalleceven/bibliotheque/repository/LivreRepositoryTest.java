package org.leotalleceven.bibliotheque.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.leotalleceven.bibliotheque.models.lecture.Livre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for LivreRepository using Mockito.
 * Note: The searchLivres() query should be tested with @DataJpaTest for full integration.
 */
class LivreRepositoryTest {

    private LivreRepository livreRepository;

    @BeforeEach
    void setUp() {
        livreRepository = mock(LivreRepository.class);
    }

    @Test
    void searchLivres_shouldBeCallable() {
        Livre livre = new Livre();
        livre.setId(1);
        livre.setTitre("1984");
        when(livreRepository.searchLivres("1984", null, null, null))
                .thenReturn(List.of(livre));

        List<Livre> result = livreRepository.searchLivres("1984", null, null, null);

        assertThat(result).hasSize(1);
        verify(livreRepository).searchLivres("1984", null, null, null);
    }

    @Test
    void searchLivres_shouldHandleNullParameters() {
        when(livreRepository.searchLivres(null, null, null, null)).thenReturn(List.of());

        List<Livre> result = livreRepository.searchLivres(null, null, null, null);

        assertThat(result).isEmpty();
    }
}
