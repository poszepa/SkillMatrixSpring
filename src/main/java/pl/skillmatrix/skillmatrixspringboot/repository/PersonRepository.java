package pl.skillmatrix.skillmatrixspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.skillmatrix.skillmatrixspringboot.model.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {


    @Modifying
    @Query(value = "INSERT INTO skill_matrix.owned_skill (gain_skill, person_id, skill_id) SELECT false,:personId, id FROM skill_matrix.skills", nativeQuery = true)
    public void copySkillsToPersonWhileCreateNewPerson(@Param("personId")Integer id);

    public Person findByExpertis(Integer expertis);


    public Person findPersonById(Integer id);

    public List<Person> findPersonsByActiveTrue();

    @Query(value = "SELECT * FROM skill_matrix.persons WHERE departments_id = :departmentID AND groups_id = :groupID AND team_id = :teamID AND active = true", nativeQuery = true)
    public List<Person> findAllPersonWithDepartmentAndGroupsAndTeams(@Param("departmentID")Integer departmentID,
                                                                     @Param("groupID")Integer groupID,
                                                                     @Param("teamID")Integer teamID);

    @Query(value = "SELECT * FROM skill_matrix.persons WHERE departments_id = :departmentID AND groups_id = :groupID AND active = true", nativeQuery = true)
    public List<Person> findAllPersonWithDepartmentAndGroups(@Param("departmentID")Integer departmentID,
                                                             @Param("groupID")Integer groupID);

    @Query(value = "SELECT * FROM skill_matrix.persons WHERE departments_id = :departmentID AND active = true", nativeQuery = true)
    public List<Person> findAllPersonWithDepartment(@Param("departmentID")Integer departmentID);

    @Query(value = "SELECT COUNT(*)\n" +
            "FROM skill_matrix.persons\n" +
            "WHERE active = true AND departments_id = :departments_id\n" +
            "GROUP BY departments_id;", nativeQuery = true)
    public Integer countPersonByDepartmentID(@Param("departments_id")Integer id);

    @Query(value = "SELECT person_id, COUNT(*) FROM owned_skill\n" +
            "JOIN skills s on owned_skill.skill_id = s.id\n" +
            "JOIN persons p on p.id = owned_skill.person_id\n" +
            "WHERE\n" +
            "    p.active = true AND\n" +
            "    p.departments_id = :personDepartment AND\n" +
            "    gain_skill = TRUE AND\n" +
            "    s.departments_in_warehouse_id = :skillDepartment AND\n" +
            "    s.is_required = TRUE\n" +
            "GROUP BY person_id\n" +
            "HAVING COUNT(*) = :countOfMustHaveGainedSkill;", nativeQuery = true)
    public List<Integer> countPersonWhoGainedAllSkillFromDepartment(@Param("personDepartment")Integer personDepartment,
                                                  @Param("skillDepartment")Integer skillDepartment,
                                                  @Param("countOfMustHaveGainedSkill")Integer countMustHaveGainedSkill);



}
