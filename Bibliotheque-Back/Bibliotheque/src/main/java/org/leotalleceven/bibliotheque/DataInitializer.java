package org.leotalleceven.bibliotheque;

import org.leotalleceven.bibliotheque.models.Auteur;
import org.leotalleceven.bibliotheque.models.Departement;
import org.leotalleceven.bibliotheque.models.Ville;
import org.leotalleceven.bibliotheque.models.lecture.Exemplaire;
import org.leotalleceven.bibliotheque.models.lecture.Livre;
import org.leotalleceven.bibliotheque.models.lecture.Ressource;
import org.leotalleceven.bibliotheque.models.lecture.Revue;
import org.leotalleceven.bibliotheque.models.lecture.Stockage;
import org.leotalleceven.bibliotheque.models.utilisateur.Enseignant;
import org.leotalleceven.bibliotheque.models.utilisateur.Etudiant;
import org.leotalleceven.bibliotheque.models.utilisateur.Particulier;
import org.leotalleceven.bibliotheque.repository.AuteurRepository;
import org.leotalleceven.bibliotheque.repository.DepartementRepository;
import org.leotalleceven.bibliotheque.repository.EnseignantRepository;
import org.leotalleceven.bibliotheque.repository.EtudiantRepository;
import org.leotalleceven.bibliotheque.repository.ExemplaireRepository;
import org.leotalleceven.bibliotheque.repository.LivreRepository;
import org.leotalleceven.bibliotheque.repository.ParticulierRepository;
import org.leotalleceven.bibliotheque.repository.RevueRepository;
import org.leotalleceven.bibliotheque.repository.StockageRepository;
import org.leotalleceven.bibliotheque.repository.VilleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
public class DataInitializer implements CommandLineRunner {

    private final LivreRepository livreRepository;
    private final AuteurRepository auteurRepository;
    private final RevueRepository revueRepository;
    private final ExemplaireRepository exemplaireRepository;
    private final StockageRepository stockageRepository;
    private final VilleRepository villeRepository;
    private final DepartementRepository departementRepository;
    private final EnseignantRepository enseignantRepository;
    private final EtudiantRepository etudiantRepository;
    private final ParticulierRepository particulierRepository;

    public DataInitializer(
            LivreRepository livreRepository,
            AuteurRepository auteurRepository,
            RevueRepository revueRepository,
            ExemplaireRepository exemplaireRepository,
            StockageRepository stockageRepository,
            VilleRepository villeRepository,
            DepartementRepository departementRepository,
            EnseignantRepository enseignantRepository,
            EtudiantRepository etudiantRepository,
            ParticulierRepository particulierRepository) {
        this.livreRepository = livreRepository;
        this.auteurRepository = auteurRepository;
        this.revueRepository = revueRepository;
        this.exemplaireRepository = exemplaireRepository;
        this.stockageRepository = stockageRepository;
        this.villeRepository = villeRepository;
        this.departementRepository = departementRepository;
        this.enseignantRepository = enseignantRepository;
        this.etudiantRepository = etudiantRepository;
        this.particulierRepository = particulierRepository;
    }

    @Override
    public void run(String... args) {
        if (livreRepository.count() > 0) {
                        seedMissingExemplaires();
                        System.out.println("[SEED] Données déjà présentes, seed principal ignoré.");
            return;
        }

        System.out.println("[SEED] Base vide, initialisation des données...");

        // Départements (ID manuel)
        departementRepository.saveAll(List.of(
                new Departement(1, "Informatique"),
                new Departement(2, "Lettres")
        ));

        // Villes (ID manuel)
        villeRepository.saveAll(List.of(
                new Ville(75001, "Paris"),
                new Ville(69001, "Lyon")
        ));

        // Auteurs (ID auto-généré, null = nouvel enregistrement)
        auteurRepository.saveAll(List.of(
                new Auteur(null, "Hugo", "Victor"),
                new Auteur(null, "Camus", "Albert"),
                new Auteur(null, "Zola", "Emile")
        ));

        List<Stockage> stockages = stockageRepository.saveAll(List.of(
                new Stockage(50, 50, 1, 1, 1, "Roman"),
                new Stockage(50, 50, 1, 2, 1, "Policier"),
                new Stockage(30, 30, 2, 1, 2, "Revues")
        ));

        // Livres (ID auto-généré via Ressource, héritage JOINED)
        List<Livre> livres = List.of(
                new Livre(
                        "Les Miserables", 15, "000123456001",
                        "978-2253005049", "Victor Hugo", "Roman", 1862),
                new Livre(
                        "Notre-Dame de Paris", 13, "000123456002",
                        "978-2253009634", "Victor Hugo", "Roman historique", 1831),
                new Livre(
                        "L Etranger", 10, "000123456003",
                        "978-2070360024", "Albert Camus", "Roman", 1942),
                new Livre(
                        "La Peste", 10, "000123456004",
                        "978-2070360315", "Albert Camus", "Roman", 1947),
                new Livre(
                        "Germinal", 12, "000123456005",
                        "978-2253006077", "Emile Zola", "Roman", 1885),
                new Livre(
                        "Nana", 11, "000123456006",
                        "978-2253004219", "Emile Zola", "Roman", 1880),
                new Livre(
                        "Madame Bovary", 10, "000123456007",
                        "978-2070413119", "Gustave Flaubert", "Roman", 1857),
                new Livre(
                        "Le Pere Goriot", 11, "000123456008",
                        "978-2253004257", "Honore de Balzac", "Roman", 1835),
                new Livre(
                        "Candide", 8, "000123456009",
                        "978-2070360352", "Voltaire", "Conte philosophique", 1759),
                new Livre(
                        "Les Fleurs du Mal", 9, "000123456010",
                        "978-2070411579", "Charles Baudelaire", "Poesie", 1857)
        );
        livres.forEach(livre -> livre.setStockage(stockages.get(0)));
        livreRepository.saveAll(livres);

        // Revues (ID auto-généré via Ressource, héritage JOINED)
        List<Revue> revues = List.of(
                new Revue("Sciences et Vie", 5, "000234567001", 1320, null),
                new Revue("Le Monde Diplomatique", 4, "000234567002", 856, null),
                new Revue("Pour la Science", 6, "000234567003", 580, null)
        );
        revues.forEach(revue -> revue.setStockage(stockages.get(2)));
        revueRepository.saveAll(revues);

        // Enseignants (hérite Particulier, SINGLE_TABLE, id primitif = 0 → persist)
        Enseignant e1 = new Enseignant(
                0, "Dupont", "Marie",
                "10 rue de Rome, Paris", "75001", "marie.dupont@univ.local",
                120, 1
        );
        Enseignant e2 = new Enseignant(
                0, "Martin", "Luc",
                "5 avenue de Lyon, Lyon", "69001", "luc.martin@univ.local",
                80, 2
        );
        enseignantRepository.saveAll(List.of(e1, e2));

        // Etudiants (hérite Particulier, SINGLE_TABLE, id primitif = 0 → persist)
        Etudiant et1 = new Etudiant(
                0, "Bernard", "Sophie",
                "23 rue des Fleurs, Paris", "75001", "sophie.bernard@etu.local",
                60, 2026
        );
        Etudiant et2 = new Etudiant(
                0, "Lopez", "Antoine",
                "42 bd de la Republique, Lyon", "69001", "antoine.lopez@etu.local",
                50, 2025
        );
        etudiantRepository.saveAll(List.of(et1, et2));

        // Particuliers (id primitif = 0 → persist)
        Particulier p1 = new Particulier(
                0, "Rouge", "Emma",
                "1 rue du Lac, Paris", "75001", "emma.rouge@mail.local",
                40
        );
        Particulier p2 = new Particulier(
                0, "Petit", "Jean",
                "14 place du Marche, Lyon", "69001", "jean.petit@mail.local",
                35
        );
        particulierRepository.saveAll(List.of(p1, p2));

                seedMissingExemplaires();

        System.out.println(
                """
                    [SEED] Données initialisées avec succès : 10 livres,
                     3 revues,
                     3 auteurs,
                     2 enseignants,
                     2 étudiants,
                     2 particuliers.
                """
        );
    }

        private void seedMissingExemplaires() {
                List<Exemplaire> missingExemplaires = new ArrayList<>();

                Stream.concat(livreRepository.findAll().stream(), revueRepository.findAll().stream())
                                .filter(ressource -> !exemplaireRepository.existsByRessourceId(ressource.getId()))
                                .forEach(ressource -> missingExemplaires.add(buildDefaultExemplaire(ressource)));

                if (missingExemplaires.isEmpty()) {
                        return;
                }

                exemplaireRepository.saveAll(missingExemplaires);
                System.out.println(
                        "[SEED] " +
                        missingExemplaires.size() +
                        " exemplaire(s) créé(s) pour compléter les ressources."
                );
        }

        private Exemplaire buildDefaultExemplaire(Ressource ressource) {
                return new Exemplaire(
                                ressource.getCodeBarre() + "-EX1",
                                "DISPONIBLE",
                                ressource,
                                LocalDate.now().toString()
                );
        }
}
