package pl.skillmatrix.skillmatrixspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.skillmatrix.skillmatrixspringboot.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
