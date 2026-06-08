package org.leotalleceven.bibliotheque.controller;

import org.leotalleceven.bibliotheque.models.Departement;
import org.leotalleceven.bibliotheque.repository.DepartementRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departements")
@CrossOrigin(origins = "http://localhost:4200")
public class DepartementController {

    private final DepartementRepository repo;

    public DepartementController(DepartementRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Departement> getAllDepartements() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Departement getDepartementById(@PathVariable Integer id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping
    public Departement createDepartement(@RequestBody Departement departement) {
        return repo.save(departement);
    }

    @PutMapping("/{id}")
    public Departement updateDepartement(@PathVariable Integer id, @RequestBody Departement departement) {
        return repo.save(departement);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartement(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}
