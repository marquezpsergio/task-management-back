package dev.sergiomarquez.taskmanagementback.repositories;

import dev.sergiomarquez.taskmanagementback.entities.User;
import dev.sergiomarquez.taskmanagementback.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<UserDetails> findFirstByEmail(String username);

    Optional<User> findByUserRole(UserRole userRole);
}
