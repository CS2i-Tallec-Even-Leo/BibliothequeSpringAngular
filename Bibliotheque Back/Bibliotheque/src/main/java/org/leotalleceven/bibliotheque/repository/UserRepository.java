package org.leotalleceven.bibliotheque.repository;

import org.leotalleceven.bibliotheque.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}