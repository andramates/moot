package ro.andramates.moot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.andramates.moot.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}