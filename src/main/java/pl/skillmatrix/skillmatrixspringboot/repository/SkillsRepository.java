package pl.skillmatrix.skillmatrixspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.skillmatrix.skillmatrixspringboot.model.Person;
import pl.skillmatrix.skillmatrixspringboot.model.Skills;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillsRepository extends JpaRepository<Skills, Integer> {


    @Query
    List<Skills> findSkillsByDepartmentsInWarehouseNameDepartment(String nameDepartment);

    @Query("SELECT skill.id FROM Skills skill WHERE skill.nameSkill = :skillName")
    Optional<Integer> findIdByNameSkill(@Param("skillName") String skillName);

    Skills findSkillsById(Integer id);

//    @Query("SELECT Person.expertis FROM Skills skill WHERE skill.nameSkill = :skillName AND skill.departmentsInWarehouse = :departmentName")
//    List<Person> findAllPersonBySkill(@Param("skillName") String skillName, @Param("departmentName") String departmentName);
}
