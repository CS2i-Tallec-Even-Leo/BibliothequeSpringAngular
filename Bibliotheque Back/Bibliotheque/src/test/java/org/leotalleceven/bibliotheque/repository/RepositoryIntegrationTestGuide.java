package org.leotalleceven.bibliotheque.repository;


/**
 * Repository tests are mainly for complex queries.
 * JpaRepository provides basic CRUD operations that are tested via integration tests.
 * 
 * EmpruntRepository, LivreRepository, and ParticulierRepository contain custom @Query methods
 * that should ideally be tested with @DataJpaTest, but this requires additional test dependencies.
 * 
 * For unit testing, controller tests mock these repositories sufficiently.
 * For integration testing, use @SpringBootTest with test containers or embedded H2.
 */
class RepositoryIntegrationTestGuide {
    // This class serves as documentation for future integration test setup
}
