package pl.skillmatrix.skillmatrixspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
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

    @Query(value = "SELECT *\n" +
            "FROM skill_matrix.skills_person_list\n" +
            "INNER JOIN skill_matrix.persons\n" +
            "ON skills_person_list.person_list_id = skill_matrix.persons.id\n" +
            "INNER JOIN skill_matrix.skills\n" +
            "ON skills_person_list.skills_list_id = skill_matrix.skills.id\n" +
            "WHERE skill_matrix.skills.departments_in_warehouse_id = :departmentID AND\n" +
            "      skill_matrix.skills.name_skill =  :skillName", nativeQuery = true)
    List<Skills> findAllSkillBySkillNameAndDepartmentName(@Param("departmentID")Integer departmentID, @Param("skillName")String skillName);

    @Query("SELECT skill FROM Skills skill WHERE skill.nameSkill = :nameSkill AND skill.departmentsInWarehouse.nameDepartment = :departmentName")
    public Skills findSkillByDepartmentNameAndNameSkill(@Param("nameSkill")String nameSkill, @Param("departmentName")String departmentName);


}
