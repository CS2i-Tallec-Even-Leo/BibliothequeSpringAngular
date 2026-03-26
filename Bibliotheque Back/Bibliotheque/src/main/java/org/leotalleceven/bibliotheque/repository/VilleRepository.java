package org.leotalleceven.bibliotheque.repository;

import org.leotalleceven.bibliotheque.models.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VilleRepository extends JpaRepository<Ville, Integer> {
}
