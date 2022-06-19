package pl.skillmatrix.skillmatrixspringboot.repository;

import com.google.common.truth.Truth;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pl.skillmatrix.skillmatrixspringboot.model.*;

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
}