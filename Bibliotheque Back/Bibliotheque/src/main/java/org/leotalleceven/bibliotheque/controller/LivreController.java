package org.leotalleceven.bibliotheque.controller;

import org.leotalleceven.bibliotheque.models.lecture.Livre;
import org.leotalleceven.bibliotheque.repository.LivreRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livres")
@CrossOrigin(origins = "http://localhost:4200")
public class LivreController {

    private final LivreRepository repo;

    public LivreController(LivreRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Livre> getAllLivres() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Livre getLivreById(@PathVariable Integer id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping
    public Livre createLivre(@RequestBody Livre livre) {
        return repo.save(livre);
    }

    @PutMapping("/{id}")
    public Livre updateLivre(@PathVariable Integer id, @RequestBody Livre livre) {
        return repo.save(livre);
    }

    @DeleteMapping("/{id}")
    public void deleteLivre(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}
