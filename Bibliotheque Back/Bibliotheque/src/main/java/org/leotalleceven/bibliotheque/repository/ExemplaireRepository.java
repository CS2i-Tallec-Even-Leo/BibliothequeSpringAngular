package org.leotalleceven.bibliotheque.repository;

import org.leotalleceven.bibliotheque.models.lecture.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer> {
	boolean existsByRessourceId(Integer ressourceId);

	List<Exemplaire> findByRessourceIdIn(List<Integer> ressourceIds);
}
