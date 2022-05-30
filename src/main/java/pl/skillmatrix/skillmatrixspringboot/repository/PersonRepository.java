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
    @Query(value = "INSERT INTO skill_matrix.skills_person_list (skills_list_id, person_list_id) SELECT skill_matrix.skills.id, :personId FROM skill_matrix.skills", nativeQuery = true)
    public void copySkillsToPerson(@Param("personId")Integer id);

    public Person findByExpertis(Integer expertis);

}
