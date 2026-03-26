package org.leotalleceven.bibliotheque.repository;

import org.leotalleceven.bibliotheque.models.lecture.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RessourceRepository extends JpaRepository<Ressource, Integer> {
}
