package org.leotalleceven.bibliotheque.repository;

import org.leotalleceven.bibliotheque.models.lecture.Revue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevueRepository extends JpaRepository<Revue, Integer> {
}
