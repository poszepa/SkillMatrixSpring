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

    @Query("SELECT ownedSkill FROM OwnedSkill ownedSkill WHERE ownedSkill.skills.id = :id AND " +
            "ownedSkill.person.active = true")
    public List<OwnedSkill> findAllPersonBySkillID(@Param("id")Integer id);

    @Query("SELECT ownedSkill FROM OwnedSkill ownedSkill WHERE " +
            "ownedSkill.skills.id = :id AND " +
            "ownedSkill.person.active = true AND " +
            "ownedSkill.person.departmentsInWarehouse.id = :departmentID")
    public List<OwnedSkill> findAllPersonBySkillIDAndDepartmentName(@Param("id")Integer id, @Param("departmentID")Integer departmentID);

    @Query("SELECT ownedSkill FROM OwnedSkill ownedSkill WHERE " +
            "ownedSkill.skills.id = :id AND " +
            "ownedSkill.person.active = true AND " +
            "ownedSkill.person.departmentsInWarehouse.id = :departmentID AND " +
            "ownedSkill.person.groupsInWarehouse.id = :groupID")
    public List<OwnedSkill> findAllPersonBySkillIDAndDepartmentIDAndGroupID(@Param("id")Integer id,
                                                                            @Param("departmentID")Integer departmentID,
                                                                            @Param("groupID")Integer groupID);

    @Query("SELECT ownedSkill FROM OwnedSkill ownedSkill WHERE " +
            "ownedSkill.skills.id = :id AND " +
            "ownedSkill.person.active = true AND " +
            "ownedSkill.person.departmentsInWarehouse.id = :departmentID AND " +
            "ownedSkill.person.groupsInWarehouse.id = :groupID AND " +
            "ownedSkill.person.teamsInWarehouse.id = :teamID")
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
