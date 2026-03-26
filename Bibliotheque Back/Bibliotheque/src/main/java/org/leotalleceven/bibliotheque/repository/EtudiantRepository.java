package org.leotalleceven.bibliotheque.repository;

import org.leotalleceven.bibliotheque.models.utilisateur.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {
}
