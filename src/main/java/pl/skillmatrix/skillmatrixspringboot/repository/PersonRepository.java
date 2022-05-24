package pl.skillmatrix.skillmatrixspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.skillmatrix.skillmatrixspringboot.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

}
