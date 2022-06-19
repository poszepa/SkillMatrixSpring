package pl.skillmatrix.skillmatrixspringboot.repository;

import com.google.common.truth.Truth;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pl.skillmatrix.skillmatrixspringboot.model.DepartmentsInWarehouse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Departments repository specification")
@DataJpaTest
class DepartmentsInWarehouseRepositoryTest {

    @Autowired private TestEntityManager testEntityManager;
    @Autowired private DepartmentsInWarehouseRepository departmentsRepository;

    @Test
    @DisplayName("Should find all department name from every department")
    public void findAllNameFromEveryDepartment_success(){
        // GIVEN
        DepartmentsInWarehouse unloading = DepartmentsInWarehouse.builder().nameDepartment("Unloading").build();
        DepartmentsInWarehouse stow = DepartmentsInWarehouse.builder().nameDepartment("Stow").build();
        DepartmentsInWarehouse pick = DepartmentsInWarehouse.builder().nameDepartment("Pick").build();
        DepartmentsInWarehouse pack = DepartmentsInWarehouse.builder().nameDepartment("Pack").build();
        DepartmentsInWarehouse shipping = DepartmentsInWarehouse.builder().nameDepartment("Shipping").build();
        DepartmentsInWarehouse retoure = DepartmentsInWarehouse.builder().nameDepartment("Retoure").build();
        DepartmentsInWarehouse quality = DepartmentsInWarehouse.builder().nameDepartment("Quality").build();
        DepartmentsInWarehouse general = DepartmentsInWarehouse.builder().nameDepartment("General").build();
        testEntityManager.persist(unloading);
        testEntityManager.persist(stow);
        testEntityManager.persist(pick);
        testEntityManager.persist(pack);
        testEntityManager.persist(shipping);
        testEntityManager.persist(retoure);
        testEntityManager.persist(quality);
        testEntityManager.persist(general);

        // ACT
        List<String> listOfNameEveryDepartment = departmentsRepository.listOfNameEveryDepartment();

        // ASSERT
        Truth.assertThat(listOfNameEveryDepartment).hasSize(8);
    }

    @Test
    @DisplayName("Should find all department name from every department and contain department name")
    public void findAllNameFromEveryDepartmentContainNameOfDepartment_success(){
        // GIVEN
        DepartmentsInWarehouse unloading = DepartmentsInWarehouse.builder().nameDepartment("Unloading").build();
        DepartmentsInWarehouse stow = DepartmentsInWarehouse.builder().nameDepartment("Stow").build();
        DepartmentsInWarehouse pick = DepartmentsInWarehouse.builder().nameDepartment("Pick").build();
        DepartmentsInWarehouse pack = DepartmentsInWarehouse.builder().nameDepartment("Pack").build();
        DepartmentsInWarehouse shipping = DepartmentsInWarehouse.builder().nameDepartment("Shipping").build();
        DepartmentsInWarehouse retoure = DepartmentsInWarehouse.builder().nameDepartment("Retoure").build();
        DepartmentsInWarehouse quality = DepartmentsInWarehouse.builder().nameDepartment("Quality").build();
        DepartmentsInWarehouse general = DepartmentsInWarehouse.builder().nameDepartment("General").build();
        testEntityManager.persist(unloading);
        testEntityManager.persist(stow);
        testEntityManager.persist(pick);
        testEntityManager.persist(pack);
        testEntityManager.persist(shipping);
        testEntityManager.persist(retoure);
        testEntityManager.persist(quality);
        testEntityManager.persist(general);

        // ACT
        List<String> listOfNameEveryDepartment = departmentsRepository.listOfNameEveryDepartment();

        // ASSERT
        Truth.assertThat(listOfNameEveryDepartment).contains(pick.getNameDepartment());
    }


    @Test
    @DisplayName("Should not find any records from departments list")
    public void findZeroRecordsFromDepartmentName() {
        //GIVEN
        //given here is empty bcs we don't wanna add any recored, we want have empty list


        //ACT
        List<String> listOfNameEveryDepartment = departmentsRepository.listOfNameEveryDepartment();


        //ASSERT
        Truth.assertThat(listOfNameEveryDepartment).isEmpty();
    }
}