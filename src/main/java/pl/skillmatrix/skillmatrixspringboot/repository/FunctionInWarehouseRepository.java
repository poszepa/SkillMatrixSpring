package pl.skillmatrix.skillmatrixspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.skillmatrix.skillmatrixspringboot.model.FunctionInWarehouse;

@Repository
public interface FunctionInWarehouseRepository extends JpaRepository<FunctionInWarehouse, Integer> {

    FunctionInWarehouse findByFunctionName(String functionName);
}
