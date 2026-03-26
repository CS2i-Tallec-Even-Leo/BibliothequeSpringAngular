package org.leotalleceven.bibliotheque.repository;

import org.leotalleceven.bibliotheque.models.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Integer> {
}
