package org.leotalleceven.bibliotheque.controller;

import org.leotalleceven.bibliotheque.models.Emprunt;
import org.leotalleceven.bibliotheque.repository.EmpruntRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emprunts")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpruntController {

    private final EmpruntRepository repo;

    public EmpruntController(EmpruntRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Emprunt> getAllEmprunts() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Emprunt getEmpruntById(@PathVariable Integer id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping
    public Emprunt createEmprunt(@RequestBody Emprunt emprunt) {
        return repo.save(emprunt);
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
}
