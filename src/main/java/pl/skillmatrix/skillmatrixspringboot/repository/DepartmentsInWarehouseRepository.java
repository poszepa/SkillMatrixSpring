package pl.skillmatrix.skillmatrixspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.skillmatrix.skillmatrixspringboot.model.DepartmentsInWarehouse;

import java.util.List;

@Repository
public interface DepartmentsInWarehouseRepository extends JpaRepository<DepartmentsInWarehouse, Integer> {

    DepartmentsInWarehouse findByNameDepartment (String nameDepartmens);

    @Query(value = "SELECT name_department FROM departments_in_warehouse", nativeQuery = true)
    List<String> listOfNameEveryDepartment();

    @Query("SELECT department FROM DepartmentsInWarehouse department WHERE department.nameDepartment NOT LIKE 'General'")
    List<DepartmentsInWarehouse> findDepartmentWithoutGeneral();
}
