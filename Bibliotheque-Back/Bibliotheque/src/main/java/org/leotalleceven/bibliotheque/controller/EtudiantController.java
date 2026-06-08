package org.leotalleceven.bibliotheque.controller;

import org.leotalleceven.bibliotheque.models.utilisateur.Etudiant;
import org.leotalleceven.bibliotheque.repository.EtudiantRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etudiants")
@CrossOrigin(origins = "http://localhost:4200")
public class EtudiantController {

    private final EtudiantRepository repo;

    public EtudiantController(EtudiantRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Etudiant> getAllEtudiants() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Etudiant getEtudiantById(@PathVariable Integer id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping
    public Etudiant createEtudiant(@RequestBody Etudiant etudiant) {
        return repo.save(etudiant);
    }

    @PutMapping("/{id}")
    public Etudiant updateEtudiant(@PathVariable Integer id, @RequestBody Etudiant etudiant) {
        etudiant.setId(id);
        return repo.save(etudiant);
    }

    @DeleteMapping("/{id}")
    public void deleteEtudiant(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}
