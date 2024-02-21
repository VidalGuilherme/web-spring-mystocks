package br.com.vidaldev.mystocks.domain.users.repositories;

import br.com.vidaldev.mystocks.domain.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByLogin(String login);
}
