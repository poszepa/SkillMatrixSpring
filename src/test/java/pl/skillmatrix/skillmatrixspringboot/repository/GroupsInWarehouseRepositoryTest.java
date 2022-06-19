package pl.skillmatrix.skillmatrixspringboot.repository;

import com.google.common.truth.Truth;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pl.skillmatrix.skillmatrixspringboot.model.GroupsInWarehouse;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Group in warehouse specificity")
class GroupsInWarehouseRepositoryTest {

    @Autowired private GroupsInWarehouseRepository groupRepository;
    @Autowired private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Should find group by name")
    public void shouldFindGroupByName_success() {
        //GIVEN
        GroupsInWarehouse a1 = GroupsInWarehouse.builder().nameGroup("A1").build();
        testEntityManager.persist(a1);

        //ACT
        GroupsInWarehouse nameGroup = groupRepository.findByNameGroup(a1.getNameGroup());

        //ASSERT
        Truth.assertThat(nameGroup).isEqualTo(a1);
    }

    @Test
    @DisplayName("Should not find group by name, when name isn't in DB")
    public void shouldFindGroupByName_failed() {
        //GIVEN
        GroupsInWarehouse a1 = GroupsInWarehouse.builder().nameGroup("A1").build();
        testEntityManager.persist(a1);

        //ACT
        GroupsInWarehouse nameGroup = groupRepository.findByNameGroup("A2");

        //ASSERT
        Truth.assertThat(a1).isNotEqualTo(nameGroup);
    }
}