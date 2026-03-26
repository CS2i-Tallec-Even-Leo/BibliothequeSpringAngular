package org.leotalleceven.bibliotheque.controller;

import org.leotalleceven.bibliotheque.models.Auteur;
import org.leotalleceven.bibliotheque.repository.AuteurRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auteurs")
@CrossOrigin(origins = "http://localhost:4200")
public class AuteurController {

    private final AuteurRepository repo;

    public AuteurController(AuteurRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Auteur> getAllAuteurs() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Auteur getAuteurById(@PathVariable Integer id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping
    public Auteur createAuteur(@RequestBody Auteur auteur) {
        return repo.save(auteur);
    }

    @PutMapping("/{id}")
    public Auteur updateAuteur(@PathVariable Integer id, @RequestBody Auteur auteur) {
        auteur.setId(id);
        return repo.save(auteur);
    }

    @DeleteMapping("/{id}")
    public void deleteAuteur(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}
