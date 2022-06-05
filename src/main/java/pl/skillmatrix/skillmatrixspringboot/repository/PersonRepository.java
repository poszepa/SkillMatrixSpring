package pl.skillmatrix.skillmatrixspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.skillmatrix.skillmatrixspringboot.model.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {


    @Query("SELECT person FROM Skills person WHERE person.departmentsInWarehouse = :departmentName AND person.nameSkill = :nameSkill")
    public List<Person> findAllPersonWithDepartmentAndSkill(@Param("departmentName")String departmentName, @Param("nameSkill")String skillName);

    @Modifying
    @Query(value = "INSERT INTO skill_matrix.owned_skill (gain_skill, person_id, skill_id) SELECT false,:personId, id FROM skill_matrix.skills", nativeQuery = true)
    public void copySkillsToPersonWhileCreateNewPerson(@Param("personId")Integer id);

    public Person findByExpertis(Integer expertis);

    @Query("SELECT person FROM Person person")
    public List<Person> everyPersonToThoseSkillNameAndDepartmentName();

    public Person findPersonById(Integer id);

    public List<Person> findPersonByActiveTrue();

}
