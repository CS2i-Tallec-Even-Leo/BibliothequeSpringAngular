package org.leotalleceven.bibliotheque.controller;

import org.leotalleceven.bibliotheque.models.lecture.Exemplaire;
import org.leotalleceven.bibliotheque.repository.ExemplaireRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exemplaires")
@CrossOrigin(origins = "http://localhost:4200")
public class ExemplaireController {

    private final ExemplaireRepository repo;

    public ExemplaireController(ExemplaireRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Exemplaire> getAllExemplaires() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Exemplaire getExemplaireById(@PathVariable Integer id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping
    public Exemplaire createExemplaire(@RequestBody Exemplaire exemplaire) {
        return repo.save(exemplaire);
    }

    @PutMapping("/{id}")
    public Exemplaire updateExemplaire(@PathVariable Integer id, @RequestBody Exemplaire exemplaire) {
        exemplaire.setId(id);
        return repo.save(exemplaire);
    }

    @DeleteMapping("/{id}")
    public void deleteExemplaire(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}
