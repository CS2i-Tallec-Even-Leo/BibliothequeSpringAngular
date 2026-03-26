package org.leotalleceven.bibliotheque.repository;

import org.leotalleceven.bibliotheque.models.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Integer> {
}
