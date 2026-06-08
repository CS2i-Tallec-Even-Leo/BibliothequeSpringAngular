package org.leotalleceven.bibliotheque.repository;

import org.leotalleceven.bibliotheque.models.Emprunt;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Integer> {
		List<Emprunt> findByParticulierIdOrderByDateEmpruntDesc(Integer particulierId);

		@Query("""
						select e from Emprunt e
						where e.statut = 'EN_COURS'
							and e.exemplaire.id = :exemplaireId
						""")
		List<Emprunt> findActiveByExemplaireId(@Param("exemplaireId") Integer exemplaireId);

		@Query("""
						select e from Emprunt e
						where e.statut = 'EN_COURS'
							and e.particulier.id = :particulierId
						""")
		List<Emprunt> findActiveByParticulierId(@Param("particulierId") Integer particulierId);

		@Query("""
						select e from Emprunt e
						where e.statut = 'EN_COURS'
							and e.dateRetourPrevue < :today
						""")
		List<Emprunt> findOverdue(@Param("today") LocalDate today);
}
