package pl.skillmatrix.skillmatrixspringboot.repository;

import com.google.common.truth.Truth;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pl.skillmatrix.skillmatrixspringboot.model.FunctionInWarehouse;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Function in warehouse specificity")
@DataJpaTest
class FunctionInWarehouseRepositoryTest {

    @Autowired private TestEntityManager entityManager;
    @Autowired private FunctionInWarehouseRepository functionRepository;


    @Test
    @DisplayName("Should return functionInWarehouse by name")
    public void FindFunctionInWarehouseByName_success() {
        //GIVEN
        FunctionInWarehouse team_leader = FunctionInWarehouse.builder().functionName("Team Leader").build();
        entityManager.persist(team_leader);

        //ACT
        FunctionInWarehouse byFunctionName = functionRepository.findByFunctionName(team_leader.getFunctionName());

        //ASSERT
        Truth.assertThat(byFunctionName.getFunctionName()).isEqualTo("Team Leader");
    }

    @Test
    @DisplayName("Should not find function when putting wrong function name")
    public void FindFuctionInWarehouseByName_failed() {
        //GIVEN
        FunctionInWarehouse team_leader = FunctionInWarehouse.builder().functionName("Team Leader").build();
        entityManager.persist(team_leader);

        //ACT
        FunctionInWarehouse team_leader1 = functionRepository.findByFunctionName("Warehouseman");

        //ASSERT
        Truth.assertThat(team_leader1).isNull();
    }

}