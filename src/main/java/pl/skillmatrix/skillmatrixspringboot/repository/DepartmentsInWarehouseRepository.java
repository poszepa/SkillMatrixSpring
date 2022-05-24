package pl.skillmatrix.skillmatrixspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.skillmatrix.skillmatrixspringboot.model.DepartmentsInWarehouse;

@Repository
public interface DepartmentsInWarehouseRepository extends JpaRepository<DepartmentsInWarehouse, Integer> {

    DepartmentsInWarehouse findByNameDepartment (String nameDepartmens);
}
