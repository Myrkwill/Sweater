package ru.myrkwill.sweater.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.myrkwill.sweater.entities.User;

/**
 * @author Mark Nagibin
 */

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByActivationCode(String code);
}
