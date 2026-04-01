package org.leotalleceven.bibliotheque.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.leotalleceven.bibliotheque.models.utilisateur.Etudiant;
import org.leotalleceven.bibliotheque.repository.ParticulierRepository;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthControllerTest {

    private ParticulierRepository particulierRepository;
    private AuthController controller;

    @BeforeEach
    void setUp() {
        particulierRepository = mock(ParticulierRepository.class);
        controller = new AuthController(particulierRepository);
        ReflectionTestUtils.setField(controller, "adminEmail", "admin@bibliotheque.local");
        ReflectionTestUtils.setField(controller, "adminPassword", "admin123");
    }

    @Test
    void loginUtilisateur_shouldFailWhenNomOrPrenomBlank() {
        AuthController.UserLoginRequest request = new AuthController.UserLoginRequest(" ", "Jean");

        assertThatThrownBy(() -> controller.loginUtilisateur(request))
                .isInstanceOf(ResponseStatusException.class)
                .satisfies(ex -> {
                    ResponseStatusException statusException = (ResponseStatusException) ex;
                    assertThat(statusException.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
                });
    }

    @Test
    void loginUtilisateur_shouldReturnUserPayloadWhenFound() {
        Etudiant etudiant = new Etudiant();
        etudiant.setId(42);
        etudiant.setNom("Durand");
        etudiant.setPrenom("Alice");
        etudiant.setEmail("alice@etu.fr");

        when(particulierRepository.findFirstByNomIgnoreCaseAndPrenomIgnoreCase("Durand", "Alice"))
                .thenReturn(Optional.of(etudiant));

        Map<String, Object> result = controller.loginUtilisateur(new AuthController.UserLoginRequest("Durand", "Alice"));

        assertThat(result.get("id")).isEqualTo(42);
        assertThat(result.get("nom")).isEqualTo("Durand");
        assertThat(result.get("prenom")).isEqualTo("Alice");
        assertThat(result.get("email")).isEqualTo("alice@etu.fr");
        assertThat(result.get("type")).isEqualTo("ETUDIANT");
    }

    @Test
    void loginUtilisateur_shouldFailWhenUserNotFound() {
        when(particulierRepository.findFirstByNomIgnoreCaseAndPrenomIgnoreCase("Durand", "Alice"))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> controller.loginUtilisateur(new AuthController.UserLoginRequest("Durand", "Alice")))
                .isInstanceOf(ResponseStatusException.class)
                .satisfies(ex -> {
                    ResponseStatusException statusException = (ResponseStatusException) ex;
                    assertThat(statusException.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
                });
    }

    @Test
    void loginAdmin_shouldFailWithInvalidCredentials() {
        AuthController.AdminLoginRequest request = new AuthController.AdminLoginRequest("admin@bibliotheque.local", "bad-password");

        assertThatThrownBy(() -> controller.loginAdmin(request))
                .isInstanceOf(ResponseStatusException.class)
                .satisfies(ex -> {
                    ResponseStatusException statusException = (ResponseStatusException) ex;
                    assertThat(statusException.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
                });
    }

    @Test
    void loginAdmin_shouldReturnRoleWhenCredentialsAreValid() {
        AuthController.AdminLoginRequest request = new AuthController.AdminLoginRequest("admin@bibliotheque.local", "admin123");

        Map<String, Object> result = controller.loginAdmin(request);

        assertThat(result.get("email")).isEqualTo("admin@bibliotheque.local");
        assertThat(result.get("role")).isEqualTo("BIBLIOTHECAIRE");
    }
}