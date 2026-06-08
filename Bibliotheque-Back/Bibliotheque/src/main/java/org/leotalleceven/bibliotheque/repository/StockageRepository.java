package org.leotalleceven.bibliotheque.repository;

import org.leotalleceven.bibliotheque.models.lecture.Stockage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockageRepository extends JpaRepository<Stockage, Integer> {
}
