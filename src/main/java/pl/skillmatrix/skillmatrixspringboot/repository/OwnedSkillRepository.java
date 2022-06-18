package pl.skillmatrix.skillmatrixspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.skillmatrix.skillmatrixspringboot.model.OwnedSkill;
import pl.skillmatrix.skillmatrixspringboot.model.Person;

import java.util.List;

public interface OwnedSkillRepository extends JpaRepository<OwnedSkill, Integer> {


    public OwnedSkill findOwnedSkillById(Long id);

    @Query(value = "SELECT skill_matrix.owned_skill.person_id,\n" +
            "       owned_skill.id,\n" +
            "       p.id,\n" +
            "       p.active,\n" +
            "       p.expertis,\n" +
            "       p.first_name,\n" +
            "       p.last_name,\n" +
            "       diw.name_department,\n" +
            "       fiw.function_name,\n" +
            "       giw.name_group,\n" +
            "       tiw.name_team,\n" +
            "       gain_skill,\n" +
            "       skill_id\n" +
            "FROM skill_matrix.owned_skill\n" +
            "JOIN persons p on owned_skill.person_id = p.id\n" +
            "JOIN skills s on owned_skill.skill_id = s.id\n" +
            "JOIN departments_in_warehouse diw on p.departments_id = diw.id\n" +
            "JOIN function_in_warehouse fiw on p.function_id = fiw.id\n" +
            "JOIN groups_in_warehouse giw on p.groups_id = giw.id\n" +
            "JOIN teams_in_warehouse tiw on p.team_id = tiw.id\n" +
            "WHERE skill_id = :id AND p.active = true", nativeQuery = true)
    public List<OwnedSkill> findAllPersonBySkillID(@Param("id")Integer id);

    @Query(value = "SELECT skill_matrix.owned_skill.person_id,\n" +
            "       owned_skill.id,\n" +
            "       p.id,\n" +
            "       p.active,\n" +
            "       p.departments_id,\n" +
            "       p.expertis,\n" +
            "       p.first_name,\n" +
            "       p.last_name,\n" +
            "       diw.name_department,\n" +
            "       fiw.function_name,\n" +
            "       giw.name_group,\n" +
            "       tiw.name_team,\n" +
            "       gain_skill,\n" +
            "       skill_id\n" +
            "FROM skill_matrix.owned_skill\n" +
            "JOIN persons p on owned_skill.person_id = p.id\n" +
            "JOIN skills s on owned_skill.skill_id = s.id\n" +
            "JOIN departments_in_warehouse diw on p.departments_id = diw.id\n" +
            "JOIN function_in_warehouse fiw on p.function_id = fiw.id\n" +
            "JOIN groups_in_warehouse giw on p.groups_id = giw.id\n" +
            "JOIN teams_in_warehouse tiw on p.team_id = tiw.id\n" +
            "WHERE skill_id = :id AND p.departments_id = :departmentID AND p.active = true", nativeQuery = true)
    public List<OwnedSkill> findAllPersonBySkillIDAndDepartmentName(@Param("id")Integer id, @Param("departmentID")Integer departmentID);

    @Query(value = "SELECT skill_matrix.owned_skill.person_id,\n" +
            "       owned_skill.id,\n" +
            "       p.id,\n" +
            "       p.active,\n" +
            "       p.departments_id,\n" +
            "       p.groups_id,\n" +
            "       p.expertis,\n" +
            "       p.first_name,\n" +
            "       p.last_name,\n" +
            "       diw.name_department,\n" +
            "       fiw.function_name,\n" +
            "       giw.name_group,\n" +
            "       tiw.name_team,\n" +
            "       gain_skill,\n" +
            "       skill_id\n" +
            "FROM skill_matrix.owned_skill\n" +
            "JOIN persons p on owned_skill.person_id = p.id\n" +
            "JOIN skills s on owned_skill.skill_id = s.id\n" +
            "JOIN departments_in_warehouse diw on p.departments_id = diw.id\n" +
            "JOIN function_in_warehouse fiw on p.function_id = fiw.id\n" +
            "JOIN groups_in_warehouse giw on p.groups_id = giw.id\n" +
            "JOIN teams_in_warehouse tiw on p.team_id = tiw.id\n" +
            "WHERE skill_id = :id AND p.active = true AND p.departments_id = :departmentID\n" +
            "AND p.groups_id = :groupID", nativeQuery = true)
    public List<OwnedSkill> findAllPersonBySkillIDAndDepartmentIDAndGroupID(@Param("id")Integer id,
                                                                            @Param("departmentID")Integer departmentID,
                                                                            @Param("groupID")Integer groupID);

    @Query(value = "SELECT skill_matrix.owned_skill.person_id,\n" +
            "       owned_skill.id,\n" +
            "       p.id,\n" +
            "       p.active,\n" +
            "       p.departments_id,\n" +
            "       p.groups_id,\n" +
            "       p.team_id,\n" +
            "       p.expertis,\n" +
            "       p.first_name,\n" +
            "       p.last_name,\n" +
            "       diw.name_department,\n" +
            "       fiw.function_name,\n" +
            "       giw.name_group,\n" +
            "       tiw.name_team,\n" +
            "       gain_skill,\n" +
            "       skill_id\n" +
            "FROM skill_matrix.owned_skill\n" +
            "JOIN persons p on owned_skill.person_id = p.id\n" +
            "JOIN skills s on owned_skill.skill_id = s.id\n" +
            "JOIN departments_in_warehouse diw on p.departments_id = diw.id\n" +
            "JOIN function_in_warehouse fiw on p.function_id = fiw.id\n" +
            "JOIN groups_in_warehouse giw on p.groups_id = giw.id\n" +
            "JOIN teams_in_warehouse tiw on p.team_id = tiw.id\n" +
            "WHERE skill_id = :id AND p.active = true AND p.departments_id = :departmentID\n" +
            "AND p.groups_id = :groupID\n" +
            "AND p.team_id = :teamID", nativeQuery = true)
    public List<OwnedSkill> findAllPersonBySkillIDAndDepartmentIDAndGroupIDAndTeamID(
            @Param("id")Integer id,
            @Param("departmentID")Integer departmentID,
            @Param("groupID")Integer groupID,
            @Param("teamID")Integer teamID);


    @Query("SELECT ownedSkill FROM OwnedSkill ownedSkill WHERE ownedSkill.gainSkill = FALSE " +
            "AND ownedSkill.person.id = :personID " +
            "AND ownedSkill.skills.isRequired = true " +
            "ORDER BY ownedSkill.skills.departmentsInWarehouse.nameDepartment")
    public List<OwnedSkill> leftRequiredSkillsForPerson(@Param("personID")Integer personID);


    @Modifying
    @Query(value = "UPDATE skill_matrix.owned_skill SET gain_skill = false WHERE skill_matrix.owned_skill.skill_id = :skillID", nativeQuery = true)
    public void afterUpdateSetGainedSkillToFalse(@Param("skillID")Integer skillId);

}
