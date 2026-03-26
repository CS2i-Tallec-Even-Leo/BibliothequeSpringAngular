package org.leotalleceven.bibliotheque.controller;

import org.leotalleceven.bibliotheque.models.utilisateur.Particulier;
import org.leotalleceven.bibliotheque.repository.ParticulierRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/particuliers")
@CrossOrigin(origins = "http://localhost:4200")
public class ParticulierController {

    private final ParticulierRepository repo;

    public ParticulierController(ParticulierRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Particulier> getAllParticuliers() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Particulier getParticulierById(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping
    public Particulier createParticulier(@RequestBody Particulier particulier) {
        return repo.save(particulier);
    }

    @PutMapping("/{id}")
    public Particulier updateParticulier(@PathVariable int id, @RequestBody Particulier particulier) {
        particulier.setId(id);
        return repo.save(particulier);
    }

    @DeleteMapping("/{id}")
    public void deleteParticulier(@PathVariable Long id) {
        repo.deleteById(id);
    }
}




