package org.leotalleceven.bibliotheque.controller;

import org.leotalleceven.bibliotheque.models.lecture.Revue;
import org.leotalleceven.bibliotheque.repository.RevueRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/revues")
@CrossOrigin(origins = "http://localhost:4200")
public class RevueController {

    private final RevueRepository repo;

    public RevueController(RevueRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Revue> getAllRevues() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Revue getRevueById(@PathVariable Integer id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping
    public Revue createRevue(@RequestBody Revue revue) {
        return repo.save(revue);
    }

    @PutMapping("/{id}")
    public Revue updateRevue(@PathVariable Integer id, @RequestBody Revue revue) {
        return repo.save(revue);
    }

    @DeleteMapping("/{id}")
    public void deleteRevue(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}
