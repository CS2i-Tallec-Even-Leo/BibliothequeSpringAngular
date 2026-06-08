package org.leotalleceven.bibliotheque.controller;

import org.leotalleceven.bibliotheque.models.utilisateur.Enseignant;
import org.leotalleceven.bibliotheque.repository.EnseignantRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enseignants")
@CrossOrigin(origins = "http://localhost:4200")
public class EnseignantController {

    private final EnseignantRepository repo;

    public EnseignantController(EnseignantRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Enseignant> getAllEnseignants() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Enseignant getEnseignantById(@PathVariable Integer id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping
    public Enseignant createEnseignant(@RequestBody Enseignant enseignant) {
        return repo.save(enseignant);
    }

    @PutMapping("/{id}")
    public Enseignant updateEnseignant(@PathVariable Integer id, @RequestBody Enseignant enseignant) {
        enseignant.setId(id);
        return repo.save(enseignant);
    }

    @DeleteMapping("/{id}")
    public void deleteEnseignant(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}
