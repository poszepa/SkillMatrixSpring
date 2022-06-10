package pl.skillmatrix.skillmatrixspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.skillmatrix.skillmatrixspringboot.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    @Query("SELECT user FROM User user WHERE user.role = :role")
    List<User> findAllByRole(@Param("role")String role);

    User findUserById(Integer userID);
}
