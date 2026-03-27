package org.leotalleceven.bibliotheque.controller;

import org.leotalleceven.bibliotheque.models.lecture.Livre;
import org.leotalleceven.bibliotheque.models.lecture.Stockage;
import org.leotalleceven.bibliotheque.repository.LivreRepository;
import org.leotalleceven.bibliotheque.repository.StockageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/livres")
@CrossOrigin(origins = "http://localhost:4200")
public class LivreController {

    private final LivreRepository repo;
    private final StockageRepository stockageRepository;

    public LivreController(LivreRepository repo, StockageRepository stockageRepository) {
        this.repo = repo;
        this.stockageRepository = stockageRepository;
    }

    @GetMapping
    public List<Livre> getAllLivres() {
        return repo.findAll();
    }

    @GetMapping("/recherche")
    public List<Livre> searchLivres(
            @RequestParam(required = false) String nomOuvrage,
            @RequestParam(required = false) String auteur,
            @RequestParam(required = false) Integer anneePublication,
            @RequestParam(required = false) String theme
    ) {
        return repo.searchLivres(blankToNull(nomOuvrage), blankToNull(auteur), anneePublication, blankToNull(theme));
    }

    @GetMapping("/{id}")
    public Livre getLivreById(@PathVariable Integer id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping
    public Livre createLivre(@RequestBody Livre livre) {
        livre.setId(null);
        livre.setStockage(resolveStockage(livre.getStockage()));
        return repo.save(livre);
    }

    @PutMapping("/{id}")
    public Livre updateLivre(@PathVariable Integer id, @RequestBody Livre livre) {
        livre.setId(id);
        livre.setStockage(resolveStockage(livre.getStockage()));
        return repo.save(livre);
    }

    @DeleteMapping("/{id}")
    public void deleteLivre(@PathVariable Integer id) {
        repo.deleteById(id);
    }

    private String blankToNull(String value) {
        return value == null || value.isBlank() ? null : value;
    }

    private Stockage resolveStockage(Stockage payloadStockage) {
        if (payloadStockage == null || payloadStockage.getId() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stockage obligatoire");
        }

        return stockageRepository.findById(payloadStockage.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stockage introuvable"));
    }
}
