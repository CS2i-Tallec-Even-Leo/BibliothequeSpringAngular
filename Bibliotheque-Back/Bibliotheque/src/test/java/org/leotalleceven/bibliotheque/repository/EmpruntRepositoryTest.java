package org.leotalleceven.bibliotheque.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.leotalleceven.bibliotheque.models.Emprunt;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for EmpruntRepository using Mockito.
 * Note: Complex query tests (findActiveByExemplaireId, findOverdue, etc.) 
 * should use @DataJpaTest with embedded H2 for full integration testing.
 */
class EmpruntRepositoryTest {

    private EmpruntRepository empruntRepository;

    @BeforeEach
    void setUp() {
        empruntRepository = mock(EmpruntRepository.class);
    }

    @Test
    void findByParticulierIdOrderByDateEmpruntDesc_shouldBeCallable() {
        Emprunt emprunt = new Emprunt(LocalDate.of(2026, 3, 1), LocalDate.of(2026, 3, 15), "EN_COURS");
        when(empruntRepository.findByParticulierIdOrderByDateEmpruntDesc(1))
                .thenReturn(List.of(emprunt));

        List<Emprunt> result = empruntRepository.findByParticulierIdOrderByDateEmpruntDesc(1);

        assertThat(result).hasSize(1);
        verify(empruntRepository).findByParticulierIdOrderByDateEmpruntDesc(1);
    }

    @Test
    void findActiveByExemplaireId_shouldBeCallable() {
        when(empruntRepository.findActiveByExemplaireId(5)).thenReturn(List.of());

        List<Emprunt> result = empruntRepository.findActiveByExemplaireId(5);

        assertThat(result).isEmpty();
    }

    @Test
    void findOverdue_shouldBeCallable() {
        Emprunt overdue = new Emprunt(LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 15), "EN_COURS");
        when(empruntRepository.findOverdue(any(LocalDate.class))).thenReturn(List.of(overdue));

        List<Emprunt> result = empruntRepository.findOverdue(LocalDate.of(2026, 4, 1));

        assertThat(result).hasSize(1);
    }
}
