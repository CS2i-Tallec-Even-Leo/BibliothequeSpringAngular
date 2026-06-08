package org.leotalleceven.bibliotheque.controller;

import org.leotalleceven.bibliotheque.models.Ville;
import org.leotalleceven.bibliotheque.repository.VilleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/villes")
@CrossOrigin(origins = "http://localhost:4200")
public class VilleController {

    private final VilleRepository repo;

    public VilleController(VilleRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Ville> getAllVilles() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Ville getVilleById(@PathVariable Integer id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping
    public Ville createVille(@RequestBody Ville ville) {
        return repo.save(ville);
    }

    @PutMapping("/{id}")
    public Ville updateVille(@PathVariable Integer id, @RequestBody Ville ville) {
        return repo.save(ville);
    }

    @DeleteMapping("/{id}")
    public void deleteVille(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}
