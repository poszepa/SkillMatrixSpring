package pl.skillmatrix.skillmatrixspringboot.repository;

import com.google.common.truth.Truth;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pl.skillmatrix.skillmatrixspringboot.model.*;

import java.util.List;

@DataJpaTest
@DisplayName("OwnedSkill repository specificity")
class OwnedSkillRepositoryTest {

    @Autowired private OwnedSkillRepository ownedSkillRepository;
    @Autowired private TestEntityManager entityManager;

    private final DepartmentsInWarehouse DEPARTMENT_IN_WAREHOUSE = DepartmentsInWarehouse.builder().nameDepartment("Pick").build();
    private final FunctionInWarehouse FUNCTION_IN_WAREHOUSE = FunctionInWarehouse.builder().functionName("Team Leader").build();
    private final GroupsInWarehouse GROUP_IN_WAREHOUSE = GroupsInWarehouse.builder().nameGroup("F").build();
    private final TeamsInWarehouse TEAM_IN_WAREHOUSE = TeamsInWarehouse.builder().nameTeam("A8").build();
    private final Person PERSON = Person.builder()
            .name("Kamil")
            .surname("Poszepczynski")
            .expertis(301190)
            .active(true)
            .departmentsInWarehouse(DEPARTMENT_IN_WAREHOUSE)
            .functionInWarehouse(FUNCTION_IN_WAREHOUSE)
            .groupsInWarehouse(GROUP_IN_WAREHOUSE)
            .build();
    private final Skills SKILLS = Skills.builder()
            .nameSkill("SOP v 0.0.1 PICK")
            .isRequired(true)
            .departmentsInWarehouse(DEPARTMENT_IN_WAREHOUSE)
            .build();
    private final OwnedSkill OWNED_SKILLS = OwnedSkill.builder()
            .gainSkill(true)
            .person(PERSON)
            .skills(SKILLS)
            .build();


    @Test
    @DisplayName("Should find OwnedSkill by ownedSkill ID")
    public void FindOwnedSkillByID_success() {
        //GIVEN
        entityManager.persist(DEPARTMENT_IN_WAREHOUSE);
        entityManager.persist(FUNCTION_IN_WAREHOUSE);
        entityManager.persist(GROUP_IN_WAREHOUSE);
        entityManager.persist(TEAM_IN_WAREHOUSE);
        entityManager.persist(PERSON);
        entityManager.persist(SKILLS);
        entityManager.persist(OWNED_SKILLS);

        //ACT
        OwnedSkill ownedSkillById = ownedSkillRepository.findOwnedSkillById(1L);

        //ASSERT
        Truth.assertThat(ownedSkillById).isEqualTo(OWNED_SKILLS);
    }


    @Test
    @DisplayName("Should find List of owned Skill with specificity skills id")
    public void FindPersonListBySkillsID_success() {
        //GIVEN
        entityManager.persist(DEPARTMENT_IN_WAREHOUSE);
        entityManager.persist(FUNCTION_IN_WAREHOUSE);
        entityManager.persist(GROUP_IN_WAREHOUSE);
        entityManager.persist(TEAM_IN_WAREHOUSE);
        entityManager.persist(PERSON);
        entityManager.persist(SKILLS);
        entityManager.persist(OWNED_SKILLS);

        //ACT
        List<OwnedSkill> allPersonBySkillID = ownedSkillRepository.findAllPersonBySkillID(SKILLS.getId());

        //ASSERT
        Truth.assertThat(allPersonBySkillID).hasSize(1);
    }

    @Test
    @DisplayName("Should not find any records from ownedSkill when skill id is wrong")
    public void FindPersonListByWrongSkillsID_failed() {
        //GIVEN
        entityManager.persist(DEPARTMENT_IN_WAREHOUSE);
        entityManager.persist(FUNCTION_IN_WAREHOUSE);
        entityManager.persist(GROUP_IN_WAREHOUSE);
        entityManager.persist(TEAM_IN_WAREHOUSE);
        entityManager.persist(PERSON);
        entityManager.persist(SKILLS);
        entityManager.persist(OWNED_SKILLS);

        //ACT
        List<OwnedSkill> allPersonBySkillID = ownedSkillRepository.findAllPersonBySkillID(Integer.MIN_VALUE);

        //ASSERT
        Truth.assertThat(allPersonBySkillID).hasSize(0);
    }

    @Test
    @DisplayName("Should find List of owned Skill with specificity skills id and person department id")
    public void FindPersonListBySkillsIDAndPersonDepartmentID_success() {
        //GIVEN
        entityManager.persist(DEPARTMENT_IN_WAREHOUSE);
        entityManager.persist(FUNCTION_IN_WAREHOUSE);
        entityManager.persist(GROUP_IN_WAREHOUSE);
        entityManager.persist(TEAM_IN_WAREHOUSE);
        entityManager.persist(PERSON);
        entityManager.persist(SKILLS);
        entityManager.persist(OWNED_SKILLS);

        //ACT
        List<OwnedSkill> allPersonBySkillID = ownedSkillRepository.findAllPersonBySkillIDAndDepartmentName(SKILLS.getId(), PERSON.getDepartmentsInWarehouse().getId());

        //ASSERT
        Truth.assertThat(allPersonBySkillID).hasSize(1);
    }

    @Test
    @DisplayName("Should NOT find List of owned Skill with wrong skills id and wrong person department id")
    public void FindPersonListByWrongSkillsIDAndWrongPersonDepartmentID_failed() {
        //GIVEN
        entityManager.persist(DEPARTMENT_IN_WAREHOUSE);
        entityManager.persist(FUNCTION_IN_WAREHOUSE);
        entityManager.persist(GROUP_IN_WAREHOUSE);
        entityManager.persist(TEAM_IN_WAREHOUSE);
        entityManager.persist(PERSON);
        entityManager.persist(SKILLS);
        entityManager.persist(OWNED_SKILLS);

        //ACT
        List<OwnedSkill> allPersonBySkillID = ownedSkillRepository.findAllPersonBySkillIDAndDepartmentName(Integer.MIN_VALUE, Integer.MIN_VALUE);

        //ASSERT
        Truth.assertThat(allPersonBySkillID).hasSize(0);
    }

    @Test
    @DisplayName("Should NOT find List of owned Skill with good skills id and wrong person department id")
    public void FindPersonListBySkillsIDAndWrongPersonDepartmentID_failed() {
        //GIVEN
        entityManager.persist(DEPARTMENT_IN_WAREHOUSE);
        entityManager.persist(FUNCTION_IN_WAREHOUSE);
        entityManager.persist(GROUP_IN_WAREHOUSE);
        entityManager.persist(TEAM_IN_WAREHOUSE);
        entityManager.persist(PERSON);
        entityManager.persist(SKILLS);
        entityManager.persist(OWNED_SKILLS);

        //ACT
        List<OwnedSkill> allPersonBySkillID = ownedSkillRepository.findAllPersonBySkillIDAndDepartmentName(SKILLS.getId(), Integer.MIN_VALUE);

        //ASSERT
        Truth.assertThat(allPersonBySkillID).hasSize(0);
    }

    @Test
    @DisplayName("Should NOT find List of owned Skill with wrong skills id and good person department id")
    public void FindPersonListByWrongSkillsIDAndGoodPersonDepartmentID_failed() {
        //GIVEN
        entityManager.persist(DEPARTMENT_IN_WAREHOUSE);
        entityManager.persist(FUNCTION_IN_WAREHOUSE);
        entityManager.persist(GROUP_IN_WAREHOUSE);
        entityManager.persist(TEAM_IN_WAREHOUSE);
        entityManager.persist(PERSON);
        entityManager.persist(SKILLS);
        entityManager.persist(OWNED_SKILLS);

        //ACT
        List<OwnedSkill> allPersonBySkillID = ownedSkillRepository.findAllPersonBySkillIDAndDepartmentName(Integer.MIN_VALUE, PERSON.getDepartmentsInWarehouse().getId());

        //ASSERT
        Truth.assertThat(allPersonBySkillID).hasSize(0);
    }

    @Test
    @DisplayName("Should  find List of owned Skill with good skills id and good person department id and good person team id")
    public void FindPersonListByGoodSkillsIDAndGoodPersonDepartmentIDAndGoodPersonTeamID_success() {
        //GIVEN
        entityManager.persist(DEPARTMENT_IN_WAREHOUSE);
        entityManager.persist(FUNCTION_IN_WAREHOUSE);
        entityManager.persist(GROUP_IN_WAREHOUSE);
        entityManager.persist(TEAM_IN_WAREHOUSE);
        entityManager.persist(PERSON);
        entityManager.persist(SKILLS);
        entityManager.persist(OWNED_SKILLS);

        //ACT
        List<OwnedSkill> allPersonBySkillID = ownedSkillRepository.findAllPersonBySkillIDAndDepartmentIDAndGroupID(SKILLS.getId(), PERSON.getDepartmentsInWarehouse().getId(), PERSON.getGroupsInWarehouse().getId());

        //ASSERT
        Truth.assertThat(allPersonBySkillID).hasSize(1);
    }

    @Test
    @DisplayName("Should NOT find List of owned Skill with WRONG skills id and good person department id and good person team id")
    public void FindPersonListByWrongSkillsIDAndGoodPersonDepartmentIDAndGoodPersonTeamID_failed() {
        //GIVEN
        entityManager.persist(DEPARTMENT_IN_WAREHOUSE);
        entityManager.persist(FUNCTION_IN_WAREHOUSE);
        entityManager.persist(GROUP_IN_WAREHOUSE);
        entityManager.persist(TEAM_IN_WAREHOUSE);
        entityManager.persist(PERSON);
        entityManager.persist(SKILLS);
        entityManager.persist(OWNED_SKILLS);
        //ACT
        List<OwnedSkill> allPersonBySkillID = ownedSkillRepository.findAllPersonBySkillIDAndDepartmentIDAndGroupID(Integer.MIN_VALUE, PERSON.getDepartmentsInWarehouse().getId(), PERSON.getGroupsInWarehouse().getId());

        //ASSERT
        Truth.assertThat(allPersonBySkillID).hasSize(0);
    }

    @Test
    @DisplayName("Should NOT find List of owned Skill with good skills id and WRONG person department id and good person team id")
    public void FindPersonListByGoodSkillsIDAndWrongPersonDepartmentIDAndGoodPersonTeamID_failed() {
        //GIVEN
        entityManager.persist(DEPARTMENT_IN_WAREHOUSE);
        entityManager.persist(FUNCTION_IN_WAREHOUSE);
        entityManager.persist(GROUP_IN_WAREHOUSE);
        entityManager.persist(TEAM_IN_WAREHOUSE);
        entityManager.persist(PERSON);
        entityManager.persist(SKILLS);
        entityManager.persist(OWNED_SKILLS);

        //ACT
        List<OwnedSkill> allPersonBySkillID = ownedSkillRepository.findAllPersonBySkillIDAndDepartmentIDAndGroupID(SKILLS.getId(), Integer.MIN_VALUE, PERSON.getGroupsInWarehouse().getId());

        //ASSERT
        Truth.assertThat(allPersonBySkillID).hasSize(0);
    }
}