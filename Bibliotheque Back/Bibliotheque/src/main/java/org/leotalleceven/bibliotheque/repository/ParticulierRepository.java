package org.leotalleceven.bibliotheque.repository;

import org.leotalleceven.bibliotheque.models.utilisateur.Particulier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticulierRepository extends JpaRepository<Particulier, Integer> {
	Optional<Particulier> findFirstByNomIgnoreCaseAndPrenomIgnoreCase(String nom, String prenom);
}