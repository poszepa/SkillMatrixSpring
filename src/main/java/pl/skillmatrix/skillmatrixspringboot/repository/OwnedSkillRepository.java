package pl.skillmatrix.skillmatrixspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import pl.skillmatrix.skillmatrixspringboot.model.OwnedSkill;

import java.util.List;

public interface OwnedSkillRepository extends JpaRepository<OwnedSkill, Integer> {


    public OwnedSkill findOwnedSkillById(Long id);

    @Query(value = "SELECT skill_matrix.owned_skill.person_id,\n" +
            "       owned_skill.id,\n" +
            "       p.id,\n" +
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
            "JOIN departments_in_warehouse diw on p.deparments_id = diw.id\n" +
            "JOIN function_in_warehouse fiw on p.function_id = fiw.id\n" +
            "JOIN groups_in_warehouse giw on p.groups_id = giw.id\n" +
            "JOIN teams_in_warehouse tiw on p.team_id = tiw.id\n" +
            "WHERE skill_id = :id", nativeQuery = true)
    public List<OwnedSkill> findAllPersonBySkillID(@Param("id")Integer id);
}
