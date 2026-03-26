package org.leotalleceven.bibliotheque.controller;

import org.leotalleceven.bibliotheque.models.lecture.Stockage;
import org.leotalleceven.bibliotheque.repository.StockageRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stockages")
@CrossOrigin(origins = "http://localhost:4200")
public class StockageController {

    private final StockageRepository repo;

    public StockageController(StockageRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Stockage> getAllStockages() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Stockage getStockageById(@PathVariable Integer id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping
    public Stockage createStockage(@RequestBody Stockage stockage) {
        return repo.save(stockage);
    }

    @PutMapping("/{id}")
    public Stockage updateStockage(@PathVariable Integer id, @RequestBody Stockage stockage) {
        return repo.save(stockage);
    }

    @DeleteMapping("/{id}")
    public void deleteStockage(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}
