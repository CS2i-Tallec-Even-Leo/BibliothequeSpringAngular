package org.leotalleceven.bibliotheque.repository;

import org.leotalleceven.bibliotheque.models.lecture.Livre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Integer> {
    @Query("""
	    select l from Livre l
	    where (:titre is null or lower(l.titre) like lower(concat('%', :titre, '%')))
	      and (:auteur is null or lower(l.Auteur) like lower(concat('%', :auteur, '%')))
	      and (:anneePublication is null or l.anneePublication = :anneePublication)
	      and (:theme is null or lower(l.Genre) like lower(concat('%', :theme, '%')))
	    """)
    List<Livre> searchLivres(
	    @Param("titre") String titre,
	    @Param("auteur") String auteur,
	    @Param("anneePublication") Integer anneePublication,
	    @Param("theme") String theme
    );
}
