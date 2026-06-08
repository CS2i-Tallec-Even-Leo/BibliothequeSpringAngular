package org.leotalleceven.bibliotheque.repository;

import org.leotalleceven.bibliotheque.models.utilisateur.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant, Integer> {
}
