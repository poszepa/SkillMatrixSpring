package pl.skillmatrix.skillmatrixspringboot.repository;

import com.google.common.truth.Truth;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pl.skillmatrix.skillmatrixspringboot.model.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("OwnedSkill repository specificity")
class OwnedSkillRepositoryTest {

    @Autowired private OwnedSkillRepository ownedSkillRepository;
    @Autowired private TestEntityManager entityManager;

    @Test
    @DisplayName("Should find OwnedSkill by ownedSkill ID")
    public void FindOwnedSkillByID_success() {
        //GIVEN
        DepartmentsInWarehouse departmentsInWarehouse = DepartmentsInWarehouse.builder().nameDepartment("Pick").build();
        entityManager.persist(departmentsInWarehouse);

        FunctionInWarehouse functionInWarehouse = FunctionInWarehouse.builder().functionName("Team Leader").build();
        entityManager.persist(functionInWarehouse);

        GroupsInWarehouse groupsInWarehouse = GroupsInWarehouse.builder().nameGroup("F").build();
        entityManager.persist(groupsInWarehouse);

        TeamsInWarehouse teamsInWarehouse = TeamsInWarehouse.builder().nameTeam("A8").build();
        entityManager.persist(teamsInWarehouse);

        Person person = Person.builder()
                .name("Kamil")
                .surname("Poszepczynski")
                .expertis(301190)
                .active(true)
                .departmentsInWarehouse(departmentsInWarehouse)
                .functionInWarehouse(functionInWarehouse)
                .groupsInWarehouse(groupsInWarehouse)
                .build();
        entityManager.persist(person);

        Skills skills = Skills.builder()
                .nameSkill("SOP v 0.0.1 PICK")
                .isRequired(true)
                .departmentsInWarehouse(departmentsInWarehouse)
                .build();
        entityManager.persist(skills);

        OwnedSkill ownedSkill = OwnedSkill.builder()
                .gainSkill(true)
                .person(person)
                .skills(skills)
                .build();
        entityManager.persist(ownedSkill);

        //ACT
        OwnedSkill ownedSkillById = ownedSkillRepository.findOwnedSkillById(1L);

        //ASSERT
        Truth.assertThat(ownedSkillById).isEqualTo(ownedSkill);
    }


    @Test
    @DisplayName("Should find List of owned Skill with specificity skills id")
    public void FindPersonListBySkillsID_success() {
        //GIVEN
        DepartmentsInWarehouse departmentsInWarehouse = DepartmentsInWarehouse.builder().nameDepartment("Pick").build();
        entityManager.persist(departmentsInWarehouse);

        FunctionInWarehouse functionInWarehouse = FunctionInWarehouse.builder().functionName("Team Leader").build();
        entityManager.persist(functionInWarehouse);

        GroupsInWarehouse groupsInWarehouse = GroupsInWarehouse.builder().nameGroup("F").build();
        entityManager.persist(groupsInWarehouse);

        TeamsInWarehouse teamsInWarehouse = TeamsInWarehouse.builder().nameTeam("A8").build();
        entityManager.persist(teamsInWarehouse);

        Person person = Person.builder()
                .name("Kamil")
                .surname("Poszepczynski")
                .expertis(301190)
                .active(true)
                .departmentsInWarehouse(departmentsInWarehouse)
                .functionInWarehouse(functionInWarehouse)
                .groupsInWarehouse(groupsInWarehouse)
                .build();
        entityManager.persist(person);

        Skills skills = Skills.builder()
                .nameSkill("SOP v 0.0.1 PICK")
                .isRequired(true)
                .departmentsInWarehouse(departmentsInWarehouse)
                .build();
        entityManager.persist(skills);

        OwnedSkill ownedSkill = OwnedSkill.builder()
                .gainSkill(true)
                .person(person)
                .skills(skills)
                .build();
        entityManager.persist(ownedSkill);

        //ACT
        List<OwnedSkill> allPersonBySkillID = ownedSkillRepository.findAllPersonBySkillID(skills.getId());

        //ASSERT
        Truth.assertThat(allPersonBySkillID).hasSize(1);
    }

    @Test
    @DisplayName("Should not find any records from ownedSkill when skill id is wrong")
    public void FindPersonListByWrongSkillsID_failed() {
        //GIVEN
        DepartmentsInWarehouse departmentsInWarehouse = DepartmentsInWarehouse.builder().nameDepartment("Pick").build();
        entityManager.persist(departmentsInWarehouse);

        FunctionInWarehouse functionInWarehouse = FunctionInWarehouse.builder().functionName("Team Leader").build();
        entityManager.persist(functionInWarehouse);

        GroupsInWarehouse groupsInWarehouse = GroupsInWarehouse.builder().nameGroup("F").build();
        entityManager.persist(groupsInWarehouse);

        TeamsInWarehouse teamsInWarehouse = TeamsInWarehouse.builder().nameTeam("A8").build();
        entityManager.persist(teamsInWarehouse);

        Person person = Person.builder()
                .name("Kamil")
                .surname("Poszepczynski")
                .expertis(301190)
                .active(true)
                .departmentsInWarehouse(departmentsInWarehouse)
                .functionInWarehouse(functionInWarehouse)
                .groupsInWarehouse(groupsInWarehouse)
                .build();
        entityManager.persist(person);

        Skills skills = Skills.builder()
                .nameSkill("SOP v 0.0.1 PICK")
                .isRequired(true)
                .departmentsInWarehouse(departmentsInWarehouse)
                .build();
        entityManager.persist(skills);

        OwnedSkill ownedSkill = OwnedSkill.builder()
                .gainSkill(true)
                .person(person)
                .skills(skills)
                .build();
        entityManager.persist(ownedSkill);

        //ACT
        List<OwnedSkill> allPersonBySkillID = ownedSkillRepository.findAllPersonBySkillID(Integer.MIN_VALUE);

        //ASSERT
        Truth.assertThat(allPersonBySkillID).hasSize(0);
    }

    @Test
    @DisplayName("Should find List of owned Skill with specificity skills id and person department id")
    public void FindPersonListBySkillsIDAndPersonDepartmentID_success() {
        //GIVEN
        DepartmentsInWarehouse departmentsInWarehouse = DepartmentsInWarehouse.builder().nameDepartment("Pick").build();
        entityManager.persist(departmentsInWarehouse);

        FunctionInWarehouse functionInWarehouse = FunctionInWarehouse.builder().functionName("Team Leader").build();
        entityManager.persist(functionInWarehouse);

        GroupsInWarehouse groupsInWarehouse = GroupsInWarehouse.builder().nameGroup("F").build();
        entityManager.persist(groupsInWarehouse);

        TeamsInWarehouse teamsInWarehouse = TeamsInWarehouse.builder().nameTeam("A8").build();
        entityManager.persist(teamsInWarehouse);

        Person person = Person.builder()
                .name("Kamil")
                .surname("Poszepczynski")
                .expertis(301190)
                .active(true)
                .departmentsInWarehouse(departmentsInWarehouse)
                .functionInWarehouse(functionInWarehouse)
                .groupsInWarehouse(groupsInWarehouse)
                .build();
        entityManager.persist(person);

        Skills skills = Skills.builder()
                .nameSkill("SOP v 0.0.1 PICK")
                .isRequired(true)
                .departmentsInWarehouse(departmentsInWarehouse)
                .build();
        entityManager.persist(skills);

        OwnedSkill ownedSkill = OwnedSkill.builder()
                .gainSkill(true)
                .person(person)
                .skills(skills)
                .build();
        entityManager.persist(ownedSkill);

        //ACT
        List<OwnedSkill> allPersonBySkillID = ownedSkillRepository.findAllPersonBySkillIDAndDepartmentName(skills.getId(), person.getDepartmentsInWarehouse().getId());

        //ASSERT
        Truth.assertThat(allPersonBySkillID).hasSize(1);
    }

    @Test
    @DisplayName("Should NOT find List of owned Skill with wrong skills id and wrong person department id")
    public void FindPersonListByWrongSkillsIDAndWrongPersonDepartmentID_failed() {
        //GIVEN
        DepartmentsInWarehouse departmentsInWarehouse = DepartmentsInWarehouse.builder().nameDepartment("Pick").build();
        entityManager.persist(departmentsInWarehouse);

        FunctionInWarehouse functionInWarehouse = FunctionInWarehouse.builder().functionName("Team Leader").build();
        entityManager.persist(functionInWarehouse);

        GroupsInWarehouse groupsInWarehouse = GroupsInWarehouse.builder().nameGroup("F").build();
        entityManager.persist(groupsInWarehouse);

        TeamsInWarehouse teamsInWarehouse = TeamsInWarehouse.builder().nameTeam("A8").build();
        entityManager.persist(teamsInWarehouse);

        Person person = Person.builder()
                .name("Kamil")
                .surname("Poszepczynski")
                .expertis(301190)
                .active(true)
                .departmentsInWarehouse(departmentsInWarehouse)
                .functionInWarehouse(functionInWarehouse)
                .groupsInWarehouse(groupsInWarehouse)
                .build();
        entityManager.persist(person);

        Skills skills = Skills.builder()
                .nameSkill("SOP v 0.0.1 PICK")
                .isRequired(true)
                .departmentsInWarehouse(departmentsInWarehouse)
                .build();
        entityManager.persist(skills);

        OwnedSkill ownedSkill = OwnedSkill.builder()
                .gainSkill(true)
                .person(person)
                .skills(skills)
                .build();
        entityManager.persist(ownedSkill);

        //ACT
        List<OwnedSkill> allPersonBySkillID = ownedSkillRepository.findAllPersonBySkillIDAndDepartmentName(Integer.MIN_VALUE, Integer.MIN_VALUE);

        //ASSERT
        Truth.assertThat(allPersonBySkillID).hasSize(0);
    }

    @Test
    @DisplayName("Should NOT find List of owned Skill with good skills id and wrong person department id")
    public void FindPersonListBySkillsIDAndWrongPersonDepartmentID_failed() {
        //GIVEN
        DepartmentsInWarehouse departmentsInWarehouse = DepartmentsInWarehouse.builder().nameDepartment("Pick").build();
        entityManager.persist(departmentsInWarehouse);

        FunctionInWarehouse functionInWarehouse = FunctionInWarehouse.builder().functionName("Team Leader").build();
        entityManager.persist(functionInWarehouse);

        GroupsInWarehouse groupsInWarehouse = GroupsInWarehouse.builder().nameGroup("F").build();
        entityManager.persist(groupsInWarehouse);

        TeamsInWarehouse teamsInWarehouse = TeamsInWarehouse.builder().nameTeam("A8").build();
        entityManager.persist(teamsInWarehouse);

        Person person = Person.builder()
                .name("Kamil")
                .surname("Poszepczynski")
                .expertis(301190)
                .active(true)
                .departmentsInWarehouse(departmentsInWarehouse)
                .functionInWarehouse(functionInWarehouse)
                .groupsInWarehouse(groupsInWarehouse)
                .build();
        entityManager.persist(person);

        Skills skills = Skills.builder()
                .nameSkill("SOP v 0.0.1 PICK")
                .isRequired(true)
                .departmentsInWarehouse(departmentsInWarehouse)
                .build();
        entityManager.persist(skills);

        OwnedSkill ownedSkill = OwnedSkill.builder()
                .gainSkill(true)
                .person(person)
                .skills(skills)
                .build();
        entityManager.persist(ownedSkill);

        //ACT
        List<OwnedSkill> allPersonBySkillID = ownedSkillRepository.findAllPersonBySkillIDAndDepartmentName(skills.getId(), Integer.MIN_VALUE);

        //ASSERT
        Truth.assertThat(allPersonBySkillID).hasSize(0);
    }

    @Test
    @DisplayName("Should NOT find List of owned Skill with wrong skills id and good person department id")
    public void FindPersonListByWrongSkillsIDAndGoodPersonDepartmentID_failed() {
        //GIVEN
        DepartmentsInWarehouse departmentsInWarehouse = DepartmentsInWarehouse.builder().nameDepartment("Pick").build();
        entityManager.persist(departmentsInWarehouse);

        FunctionInWarehouse functionInWarehouse = FunctionInWarehouse.builder().functionName("Team Leader").build();
        entityManager.persist(functionInWarehouse);

        GroupsInWarehouse groupsInWarehouse = GroupsInWarehouse.builder().nameGroup("F").build();
        entityManager.persist(groupsInWarehouse);

        TeamsInWarehouse teamsInWarehouse = TeamsInWarehouse.builder().nameTeam("A8").build();
        entityManager.persist(teamsInWarehouse);

        Person person = Person.builder()
                .name("Kamil")
                .surname("Poszepczynski")
                .expertis(301190)
                .active(true)
                .departmentsInWarehouse(departmentsInWarehouse)
                .functionInWarehouse(functionInWarehouse)
                .groupsInWarehouse(groupsInWarehouse)
                .build();
        entityManager.persist(person);

        Skills skills = Skills.builder()
                .nameSkill("SOP v 0.0.1 PICK")
                .isRequired(true)
                .departmentsInWarehouse(departmentsInWarehouse)
                .build();
        entityManager.persist(skills);

        OwnedSkill ownedSkill = OwnedSkill.builder()
                .gainSkill(true)
                .person(person)
                .skills(skills)
                .build();
        entityManager.persist(ownedSkill);

        //ACT
        List<OwnedSkill> allPersonBySkillID = ownedSkillRepository.findAllPersonBySkillIDAndDepartmentName(Integer.MIN_VALUE, person.getDepartmentsInWarehouse().getId());

        //ASSERT
        Truth.assertThat(allPersonBySkillID).hasSize(0);
    }
}