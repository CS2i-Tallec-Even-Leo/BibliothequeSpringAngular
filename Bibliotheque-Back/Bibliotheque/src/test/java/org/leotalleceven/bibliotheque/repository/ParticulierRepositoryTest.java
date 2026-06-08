package org.leotalleceven.bibliotheque.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.leotalleceven.bibliotheque.models.utilisateur.Particulier;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for ParticulierRepository using Mockito.
 * Note: The findFirstByNomIgnoreCaseAndPrenomIgnoreCase() query should be tested 
 * with @DataJpaTest for comprehensive integration testing.
 */
class ParticulierRepositoryTest {

    private ParticulierRepository particulierRepository;

    @BeforeEach
    void setUp() {
        particulierRepository = mock(ParticulierRepository.class);
    }

    @Test
    void findFirstByNomIgnoreCaseAndPrenomIgnoreCase_shouldBeCallable() {
        Particulier user = new Particulier();
        user.setId(1);
        user.setNom("Dupont");
        
        when(particulierRepository.findFirstByNomIgnoreCaseAndPrenomIgnoreCase("dupont", "jean"))
                .thenReturn(Optional.of(user));

        Optional<Particulier> result = particulierRepository.findFirstByNomIgnoreCaseAndPrenomIgnoreCase("dupont", "jean");

        assertThat(result).isPresent();
        verify(particulierRepository).findFirstByNomIgnoreCaseAndPrenomIgnoreCase("dupont", "jean");
    }

    @Test
    void findFirstByNomIgnoreCaseAndPrenomIgnoreCase_shouldReturnEmptyWhenNotFound() {
        when(particulierRepository.findFirstByNomIgnoreCaseAndPrenomIgnoreCase("unknown", "person"))
                .thenReturn(Optional.empty());

        Optional<Particulier> result = particulierRepository.findFirstByNomIgnoreCaseAndPrenomIgnoreCase("unknown", "person");

        assertThat(result).isEmpty();
    }
}
