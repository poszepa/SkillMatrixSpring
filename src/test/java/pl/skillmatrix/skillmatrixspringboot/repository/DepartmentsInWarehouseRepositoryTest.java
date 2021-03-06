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
    public void findZeroRecordsFromDepartmentName_success() {
        //GIVEN
        //given here is empty bcs we don't want to add any recored, we want to have empty list


        //ACT
        List<String> listOfNameEveryDepartment = departmentsRepository.listOfNameEveryDepartment();


        //ASSERT
        Truth.assertThat(listOfNameEveryDepartment).isEmpty();
    }

    @Test
    @DisplayName("Should find department by department name")
    public void findDepartmentByDepartmentName() {
        //GIVEN
        DepartmentsInWarehouse pick = DepartmentsInWarehouse.builder().nameDepartment("pick").build();
        testEntityManager.persist(pick);

        //ACT
        DepartmentsInWarehouse findDepartment = departmentsRepository.findByNameDepartment(pick.getNameDepartment());

        //ASSERT
        Truth.assertThat(findDepartment.getNameDepartment()).isEqualTo("pick");
    }

    @Test
    @DisplayName("Should not find department with wrong name")
    public void findDepartmentWithWrongName() {
        //GIVEN
        DepartmentsInWarehouse pick = DepartmentsInWarehouse.builder().nameDepartment("pick").build();
        testEntityManager.persist(pick);

        //ACT
        DepartmentsInWarehouse stow = departmentsRepository.findByNameDepartment("stow");

        //ASSERT

        Truth.assertThat(stow).isNull();
    }


    @Test
    @DisplayName("Should find List of department without department with name general")
    public void findDepartmentWithoutDepartmentWithNameGeneral_success() {
        //GIVEN
        DepartmentsInWarehouse pick = DepartmentsInWarehouse.builder().nameDepartment("pick").build();
        testEntityManager.persist(pick);
        DepartmentsInWarehouse general = DepartmentsInWarehouse.builder().nameDepartment("General").build();
        testEntityManager.persist(general);

        //ACT
        List<DepartmentsInWarehouse> departmentWithoutGeneral = departmentsRepository.findDepartmentWithoutGeneral();

        //ASSERT
        Truth.assertThat(departmentWithoutGeneral).doesNotContain(general);
    }

    @Test
    @DisplayName("Should have list -1 to size when Department have a name 'General'")
    public void findDepartmentWithoutGeneralSizeListMinusOne() {
        //GIVEN
        DepartmentsInWarehouse pick = DepartmentsInWarehouse.builder().nameDepartment("Pick").build();
        testEntityManager.persist(pick);

        DepartmentsInWarehouse general = DepartmentsInWarehouse.builder().nameDepartment("General").build();
        testEntityManager.persist(general);

        //ACT
        List<DepartmentsInWarehouse> departmentWithoutGeneral = departmentsRepository.findDepartmentWithoutGeneral();

        //ASSERT
        Truth.assertThat(departmentWithoutGeneral).hasSize(1);

    }
    @Test
    @DisplayName("Should list have the same size when department not have object with name general")
    public void findDepartmentWithoutGeneralSizeList() {
        //GIVEN
        DepartmentsInWarehouse pick = DepartmentsInWarehouse.builder().nameDepartment("Pick").build();
        testEntityManager.persist(pick);

        DepartmentsInWarehouse stow = DepartmentsInWarehouse.builder().nameDepartment("Stow").build();
        testEntityManager.persist(stow);

        //ACT
        List<DepartmentsInWarehouse> departmentWithoutGeneral = departmentsRepository.findDepartmentWithoutGeneral();

        //ASSERT
        Truth.assertThat(departmentWithoutGeneral).hasSize(2);
    }
}