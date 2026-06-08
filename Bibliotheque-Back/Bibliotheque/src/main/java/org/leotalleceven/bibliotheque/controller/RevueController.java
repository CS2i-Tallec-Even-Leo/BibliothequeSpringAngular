package org.leotalleceven.bibliotheque.controller;

import org.leotalleceven.bibliotheque.models.lecture.Revue;
import org.leotalleceven.bibliotheque.models.lecture.Stockage;
import org.leotalleceven.bibliotheque.repository.RevueRepository;
import org.leotalleceven.bibliotheque.repository.StockageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/revues")
@CrossOrigin(origins = "http://localhost:4200")
public class RevueController {

    private final RevueRepository repo;
    private final StockageRepository stockageRepository;

    public RevueController(RevueRepository repo, StockageRepository stockageRepository) {
        this.repo = repo;
        this.stockageRepository = stockageRepository;
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
        revue.setId(null);
        revue.setStockage(resolveStockage(revue.getStockage()));
        return repo.save(revue);
    }

    @PutMapping("/{id}")
    public Revue updateRevue(@PathVariable Integer id, @RequestBody Revue revue) {
        revue.setId(id);
        revue.setStockage(resolveStockage(revue.getStockage()));
        return repo.save(revue);
    }

    @DeleteMapping("/{id}")
    public void deleteRevue(@PathVariable Integer id) {
        repo.deleteById(id);
    }

    private Stockage resolveStockage(Stockage payloadStockage) {
        if (payloadStockage == null || payloadStockage.getId() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stockage obligatoire");
        }

        return stockageRepository.findById(payloadStockage.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stockage introuvable"));
    }
}
