package org.leotalleceven.bibliotheque.repository;

import org.leotalleceven.bibliotheque.models.utilisateur.Particulier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticulierRepository extends JpaRepository<Particulier, Long> {
}