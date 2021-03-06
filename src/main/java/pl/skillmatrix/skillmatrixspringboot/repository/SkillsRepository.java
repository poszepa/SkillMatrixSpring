package pl.skillmatrix.skillmatrixspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query("SELECT skill FROM Skills skill WHERE skill.nameSkill = :nameSkill AND skill.departmentsInWarehouse.id = :departmentID")
    public Skills findSkillByDepartmentIDNameAndNameSkill(@Param("nameSkill")String nameSkill, @Param("departmentID")Integer departmentID);

    @Modifying
    @Query(value = "INSERT INTO skill_matrix.owned_skill (gain_skill, skill_id, person_id) SELECT false, :skillId, id FROM skill_matrix.persons", nativeQuery = true)
    public void copySkillsToPersonWhileCreateNewSkills(@Param("skillId")Integer id);

    @Query(value = "SELECT * FROM skill_matrix.skills ORDER BY update_time DESC LIMIT 10", nativeQuery = true)
    public List<Skills> skillsLatestTenUpdatedSkills();

    @Query(value = "SELECT COUNT(*)\n" +
            "FROM owned_skill\n" +
            "JOIN persons p on p.id = owned_skill.person_id\n" +
            "JOIN skills s on s.id = owned_skill.skill_id\n" +
            "JOIN departments_in_warehouse diw on s.departments_in_warehouse_id = diw.id\n" +
            "WHERE s.is_required = true AND name_department = :departmentName AND person_id = :PersonID AND gain_skill = :gainSkill", nativeQuery = true)
    public Integer countEverySkillWhereHeIsRequiredAndChoosedGainSkill(@Param("PersonID")Integer personID,
                                                                           @Param("departmentName")String departmentName,
                                                                           @Param("gainSkill")Boolean gainSkill);

    @Query(value = "SELECT COUNT(*)\n" +
            "FROM owned_skill\n" +
            "JOIN persons p on p.id = owned_skill.person_id\n" +
            "JOIN skills s on s.id = owned_skill.skill_id\n" +
            "JOIN departments_in_warehouse diw on s.departments_in_warehouse_id = diw.id\n" +
            "WHERE s.is_required = true AND name_department = :departmentName AND person_id = :PersonID", nativeQuery = true)
    public Integer countEverySkillWhereHeIsRequired(@Param("PersonID")Integer personID,
                                                    @Param("departmentName")String departmentName);


    @Query(value = "SELECT COUNT(*)\n" +
            "FROM owned_skill\n" +
            "JOIN persons p on p.id = owned_skill.person_id\n" +
            "JOIN skills s on s.id = owned_skill.skill_id\n" +
            "JOIN departments_in_warehouse diw on s.departments_in_warehouse_id = diw.id\n" +
            "WHERE name_department = :departmentName AND person_id = :PersonID AND gain_skill = :gainSkill", nativeQuery = true)
    public Integer countEverySkillWithChoosedDepartmentWithGainSkill(@Param("PersonID")Integer personID,
                                                        @Param("departmentName")String departmentName,
                                                        @Param("gainSkill")Boolean gainSkill);

    @Query(value = "SELECT COUNT(*)\n" +
            "FROM owned_skill\n" +
            "JOIN persons p on p.id = owned_skill.person_id\n" +
            "JOIN skills s on s.id = owned_skill.skill_id\n" +
            "JOIN departments_in_warehouse diw on s.departments_in_warehouse_id = diw.id\n" +
            "WHERE name_department = :departmentName AND person_id = :PersonID", nativeQuery = true)
    public Integer countEverySkillChoosedDepartment(@Param("PersonID")Integer personID,
                                                    @Param("departmentName")String departmentName);

    @Query(value = "SELECT COUNT(*)\n" +
            "FROM skill_matrix.skills\n" +
            "WHERE is_required = true AND departments_in_warehouse_id = :skillDepartmentID", nativeQuery = true)
    public Integer countSkillRequiredOnSpecifyDepartment(@Param("skillDepartmentID") Integer skillDepartment);
}
