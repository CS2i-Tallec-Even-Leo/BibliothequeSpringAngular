package org.leotalleceven.bibliotheque.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.leotalleceven.bibliotheque.models.Emprunt;
import org.leotalleceven.bibliotheque.models.lecture.Exemplaire;
import org.leotalleceven.bibliotheque.models.lecture.Livre;
import org.leotalleceven.bibliotheque.models.utilisateur.Particulier;
import org.leotalleceven.bibliotheque.repository.EmpruntRepository;
import org.leotalleceven.bibliotheque.repository.ExemplaireRepository;
import org.leotalleceven.bibliotheque.repository.ParticulierRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EmpruntControllerTest {

    private EmpruntRepository empruntRepository;
    private ParticulierRepository particulierRepository;
    private ExemplaireRepository exemplaireRepository;
    private EmpruntController controller;

    @BeforeEach
    void setUp() {
        empruntRepository = mock(EmpruntRepository.class);
        particulierRepository = mock(ParticulierRepository.class);
        exemplaireRepository = mock(ExemplaireRepository.class);

        @SuppressWarnings("unchecked")
        ObjectProvider<org.springframework.mail.javamail.JavaMailSender> mailSenderProvider = mock(ObjectProvider.class);
        when(mailSenderProvider.getIfAvailable()).thenReturn(null);

        controller = new EmpruntController(empruntRepository, particulierRepository, exemplaireRepository, mailSenderProvider);
    }

    @Test
    void createEmprunt_shouldFailWhenUtilisateurIsMissing() {
        EmpruntController.EmpruntCreateRequest request = new EmpruntController.EmpruntCreateRequest(
                LocalDate.now(),
                null,
                new EmpruntController.IdPayload(null),
                new EmpruntController.IdPayload(2)
        );

        assertThatThrownBy(() -> controller.createEmprunt(request))
                .isInstanceOf(ResponseStatusException.class)
                .satisfies(ex -> {
                    ResponseStatusException statusException = (ResponseStatusException) ex;
                    assertThat(statusException.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
                });
    }

    @Test
    void createEmprunt_shouldFailWhenExemplaireAlreadyBorrowed() {
        Particulier particulier = buildParticulier(1, 100);
        Exemplaire exemplaire = buildExemplaire(2, 10, 50);

        Emprunt activeBorrow = new Emprunt();
        activeBorrow.setStatut("EN_COURS");

        when(particulierRepository.findById(1)).thenReturn(Optional.of(particulier));
        when(exemplaireRepository.findById(2)).thenReturn(Optional.of(exemplaire));
        when(empruntRepository.findActiveByExemplaireId(2)).thenReturn(List.of(activeBorrow));

        EmpruntController.EmpruntCreateRequest request = new EmpruntController.EmpruntCreateRequest(
                LocalDate.now(),
                null,
                new EmpruntController.IdPayload(1),
                new EmpruntController.IdPayload(2)
        );

        assertThatThrownBy(() -> controller.createEmprunt(request))
                .isInstanceOf(ResponseStatusException.class)
                .satisfies(ex -> {
                    ResponseStatusException statusException = (ResponseStatusException) ex;
                    assertThat(statusException.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
                });
    }

    @Test
    void createEmprunt_shouldFailWhenUserHasOverdueBorrow() {
        Particulier particulier = buildParticulier(1, 100);
        Exemplaire exemplaire = buildExemplaire(2, 10, 50);

        Emprunt overdue = new Emprunt();
        overdue.setDateRetourPrevue(LocalDate.now().minusDays(1));
        overdue.setStatut("EN_COURS");

        when(particulierRepository.findById(1)).thenReturn(Optional.of(particulier));
        when(exemplaireRepository.findById(2)).thenReturn(Optional.of(exemplaire));
        when(empruntRepository.findActiveByExemplaireId(2)).thenReturn(List.of());
        when(empruntRepository.findActiveByParticulierId(1)).thenReturn(List.of(overdue));

        EmpruntController.EmpruntCreateRequest request = new EmpruntController.EmpruntCreateRequest(
                LocalDate.now(),
                null,
                new EmpruntController.IdPayload(1),
                new EmpruntController.IdPayload(2)
        );

        assertThatThrownBy(() -> controller.createEmprunt(request))
                .isInstanceOf(ResponseStatusException.class)
                .satisfies(ex -> {
                    ResponseStatusException statusException = (ResponseStatusException) ex;
                    assertThat(statusException.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
                });
    }

    @Test
    void createEmprunt_shouldCreateBorrowWhenRulesAreSatisfied() {
        Particulier particulier = buildParticulier(1, 200);
        Exemplaire exemplaire = buildExemplaire(2, 99, 50);

        when(particulierRepository.findById(1)).thenReturn(Optional.of(particulier));
        when(exemplaireRepository.findById(2)).thenReturn(Optional.of(exemplaire));
        when(empruntRepository.findActiveByExemplaireId(2)).thenReturn(List.of());
        when(empruntRepository.findActiveByParticulierId(1)).thenReturn(List.of());
        when(empruntRepository.save(any(Emprunt.class))).thenAnswer(invocation -> invocation.getArgument(0));

        LocalDate dateEmprunt = LocalDate.of(2026, 4, 1);
        EmpruntController.EmpruntCreateRequest request = new EmpruntController.EmpruntCreateRequest(
                dateEmprunt,
                null,
                new EmpruntController.IdPayload(1),
                new EmpruntController.IdPayload(2)
        );

        Emprunt created = controller.createEmprunt(request);

        assertThat(created.getParticulier()).isSameAs(particulier);
        assertThat(created.getExemplaire()).isSameAs(exemplaire);
        assertThat(created.getDateEmprunt()).isEqualTo(dateEmprunt);
        assertThat(created.getDateRetourPrevue()).isEqualTo(dateEmprunt.plusDays(15));
        assertThat(created.getDateRetourEffectif()).isNull();
        assertThat(created.getStatut()).isEqualTo("EN_COURS");
    }

    @Test
    void notifierRetards_shouldReturnSimulationModeWithoutMailSender() {
        Emprunt overdue = new Emprunt();
        overdue.setDateRetourPrevue(LocalDate.now().minusDays(2));

        Particulier utilisateur = new Particulier();
        utilisateur.setNom("Martin");
        utilisateur.setEmail("martin@example.com");
        overdue.setParticulier(utilisateur);

        Exemplaire exemplaire = buildExemplaire(5, 22, 10);
        overdue.setExemplaire(exemplaire);

        when(empruntRepository.findOverdue(any(LocalDate.class))).thenReturn(List.of(overdue));

        Map<String, Object> result = controller.notifierRetards();

        assertThat(result.get("retards")).isEqualTo(1);
        assertThat(result.get("notificationsEnvoyees")).isEqualTo(1);
        assertThat(result.get("mode")).isEqualTo("simulation");
    }

    private Particulier buildParticulier(int id, int caution) {
        Particulier particulier = new Particulier();
        particulier.setId(id);
        particulier.setCaution(caution);
        particulier.setNom("User");
        particulier.setPrenom("Test");
        particulier.setEmail("user@example.com");
        return particulier;
    }

    private Exemplaire buildExemplaire(int exemplaireId, int ressourceId, int cautionRessource) {
        Livre livre = new Livre();
        livre.setId(ressourceId);
        livre.setTitre("Titre test");
        livre.setCaution(cautionRessource);

        Exemplaire exemplaire = new Exemplaire();
        exemplaire.setId(exemplaireId);
        exemplaire.setRessource(livre);
        return exemplaire;
    }
}