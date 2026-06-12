package org.leotalleceven.bibliotheque.controller;

import org.leotalleceven.bibliotheque.models.Emprunt;
import org.leotalleceven.bibliotheque.models.lecture.Exemplaire;
import org.leotalleceven.bibliotheque.models.utilisateur.Particulier;
import org.leotalleceven.bibliotheque.repository.EmpruntRepository;
import org.leotalleceven.bibliotheque.repository.ExemplaireRepository;
import org.leotalleceven.bibliotheque.repository.ParticulierRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/emprunts")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpruntController {

    private final EmpruntRepository repo;
    private final ParticulierRepository particulierRepository;
    private final ExemplaireRepository exemplaireRepository;
    private final JavaMailSender mailSender;

    @Value("${library.notifications.from:no-reply@bibliotheque.local}")
    private String fromEmail;

    public EmpruntController(
            EmpruntRepository repo,
            ParticulierRepository particulierRepository,
            ExemplaireRepository exemplaireRepository,
            ObjectProvider<JavaMailSender> mailSenderProvider
    ) {
        this.repo = repo;
        this.particulierRepository = particulierRepository;
        this.exemplaireRepository = exemplaireRepository;
        this.mailSender = mailSenderProvider.getIfAvailable();
    }

    @GetMapping
    public List<Emprunt> getAllEmprunts() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Emprunt getEmpruntById(@PathVariable Integer id) {
        return repo.findById(id).orElse(null);
    }

    @GetMapping("/utilisateur/{particulierId}")
    public List<Emprunt> getEmpruntsByUtilisateur(@PathVariable Integer particulierId) {
        return repo.findByParticulierIdOrderByDateEmpruntDesc(particulierId);
    }

    @GetMapping("/retards")
    public List<Emprunt> getEmpruntsEnRetard() {
        return repo.findOverdue(LocalDate.now());
    }

    @PostMapping
    public Emprunt createEmprunt(@RequestBody EmpruntCreateRequest request) {
        if (request == null ||
            request.particulier() == null ||
            request.particulier().id() == null ||
            request.particulier().id() <= 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Utilisateur obligatoire");
        }
        if (request.exemplaire() == null || request.exemplaire().id() == null || request.exemplaire().id() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Exemplaire obligatoire");
        }

        Particulier particulier = particulierRepository.findById(request.particulier().id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable"));

        Exemplaire exemplaire = exemplaireRepository.findById(request.exemplaire().id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exemplaire introuvable"));

        if (repo.findActiveByExemplaireId(exemplaire.getId()).stream().findFirst().isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cet exemplaire est deja emprunte");
        }

        List<Emprunt> empruntsActifsUtilisateur = repo.findActiveByParticulierId(particulier.getId());
        boolean hasOverdue = empruntsActifsUtilisateur.stream()
                .anyMatch(e -> e.getDateRetourPrevue() != null && e.getDateRetourPrevue().isBefore(LocalDate.now()));
        if (hasOverdue) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Impossible d'emprunter: des retards existent");
        }

        Integer ressourceId = exemplaire.getRessource() != null ? exemplaire.getRessource().getId() : null;
        boolean alreadyBorrowedSameRessource = empruntsActifsUtilisateur.stream().anyMatch(e ->
                e.getExemplaire() != null
                        && e.getExemplaire().getRessource() != null
                        && e.getExemplaire().getRessource().getId() != null
                        && e.getExemplaire().getRessource().getId().equals(ressourceId)
        );
        if (alreadyBorrowedSameRessource) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Un exemplaire de cette ressource est deja emprunte"
            );
        }

        int cautionRessource = exemplaire.getRessource() != null ? exemplaire.getRessource().getCaution() : 0;
        if (particulier.getCaution() < cautionRessource) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Caution insuffisante pour cet emprunt");
        }

        LocalDate dateEmprunt = Optional.ofNullable(request.dateEmprunt()).orElse(LocalDate.now());

        Emprunt emprunt = new Emprunt();

        emprunt.setParticulier(particulier);
        emprunt.setExemplaire(exemplaire);
        emprunt.setDateEmprunt(dateEmprunt);
        emprunt.setDateRetourPrevue(dateEmprunt.plusDays(15));
        emprunt.setDateRetourEffectif(null);
        emprunt.setStatut("EN_COURS");

        return repo.save(emprunt);
    }

    public record EmpruntCreateRequest(
            LocalDate dateEmprunt,
            String statut,
            IdPayload particulier,
            IdPayload exemplaire
    ) { }

    public record IdPayload(Integer id) {
    }

    @PostMapping("/{id}/retour")
    public Emprunt enregistrerRetour(@PathVariable Integer id) {
        Emprunt emprunt = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Emprunt introuvable"));

        emprunt.setDateRetourEffectif(LocalDate.now());
        emprunt.setStatut("RETOURNE");
        return repo.save(emprunt);
    }

    @PostMapping("/retards/notifier")
    public Map<String, Object> notifierRetards() {
        List<Emprunt> overdues = repo.findOverdue(LocalDate.now());
        int notifiedCount = 0;

        for (Emprunt emprunt : overdues) {
            Particulier utilisateur = emprunt.getParticulier();
            if (utilisateur == null || utilisateur.getEmail() == null || utilisateur.getEmail().isBlank()) {
                continue;
            }

            if (mailSender != null) {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(fromEmail);
                message.setTo(utilisateur.getEmail());
                message.setSubject("Bibliotheque - Rappel de retour");
                message.setText(buildReminderBody(utilisateur.getNom(), emprunt));
                mailSender.send(message);
            } else {
                System.out.println(
                        "[MAIL-SIMULE] Vers " +
                        utilisateur.getEmail() +
                        " : " +
                        buildReminderBody(utilisateur.getNom(), emprunt)
                );
            }

            notifiedCount++;
        }

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("retards", overdues.size());
        result.put("notificationsEnvoyees", notifiedCount);
        result.put("mode", mailSender != null ? "smtp" : "simulation");
        return result;
    }

    @PutMapping("/{id}")
    public Emprunt updateEmprunt(@PathVariable Integer id, @RequestBody Emprunt emprunt) {
        emprunt.setId(id);
        return repo.save(emprunt);
    }

    @DeleteMapping("/{id}")
    public void deleteEmprunt(@PathVariable Integer id) {
        repo.deleteById(id);
    }

    private String buildReminderBody(String nom, Emprunt emprunt) {
        String titre = emprunt.getExemplaire() != null && emprunt.getExemplaire().getRessource() != null
                ? emprunt.getExemplaire().getRessource().getTitre()
                : "ressource";
        return "Bonjour " + nom + ",\n\n"
                + "Vous avez un retard de retour pour: " + titre + ".\n"
                + "Date retour prevue: " + emprunt.getDateRetourPrevue() + "\n\n"
                + "Merci de regulariser votre emprunt au plus vite.\n"
                + "Bibliotheque universitaire";
    }
}
