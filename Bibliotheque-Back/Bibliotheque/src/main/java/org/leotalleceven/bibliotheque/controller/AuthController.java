package org.leotalleceven.bibliotheque.controller;

import org.leotalleceven.bibliotheque.models.utilisateur.Enseignant;
import org.leotalleceven.bibliotheque.models.utilisateur.Etudiant;
import org.leotalleceven.bibliotheque.models.utilisateur.Particulier;
import org.leotalleceven.bibliotheque.repository.ParticulierRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final ParticulierRepository particulierRepository;

    @Value("${library.admin.email:admin@bibliotheque.local}")
    private String adminEmail;

    @Value("${library.admin.password:admin123}")
    private String adminPassword;

    public AuthController(ParticulierRepository particulierRepository) {
        this.particulierRepository = particulierRepository;
    }

    @PostMapping("/user-login")
    public Map<String, Object> loginUtilisateur(@RequestBody UserLoginRequest request) {
        if (isBlank(request.nom()) || isBlank(request.prenom())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nom et prenom sont obligatoires");
        }

        Particulier utilisateur = particulierRepository
                .findFirstByNomIgnoreCaseAndPrenomIgnoreCase(request.nom().trim(), request.prenom().trim())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Compte utilisateur introuvable")
                );

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("id", utilisateur.getId());
        result.put("nom", utilisateur.getNom());
        result.put("prenom", utilisateur.getPrenom());
        result.put("email", utilisateur.getEmail());
        result.put("type", resolveUserType(utilisateur));
        return result;
    }

    @PostMapping("/admin-login")
    public Map<String, Object> loginAdmin(@RequestBody AdminLoginRequest request) {
        if (request == null || isBlank(request.email()) || isBlank(request.password())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email et mot de passe obligatoires");
        }

        boolean valid = adminEmail.equalsIgnoreCase(request.email().trim())
                && adminPassword.equals(request.password());

        if (!valid) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Identifiants bibliothecaire invalides");
        }

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("email", adminEmail);
        result.put("role", "BIBLIOTHECAIRE");
        return result;
    }

    private String resolveUserType(Particulier utilisateur) {
        if (utilisateur instanceof Enseignant) {
            return "ENSEIGNANT";
        }
        if (utilisateur instanceof Etudiant) {
            return "ETUDIANT";
        }
        return "PARTICULIER";
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    public record UserLoginRequest(String nom, String prenom) {
    }

    public record AdminLoginRequest(String email, String password) {
    }
}
