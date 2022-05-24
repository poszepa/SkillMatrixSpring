package pl.skillmatrix.skillmatrixspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.skillmatrix.skillmatrixspringboot.model.GroupsInWarehouse;

@Repository
public interface GroupsInWarehouseRepository extends JpaRepository<GroupsInWarehouse, Integer> {
    GroupsInWarehouse findByNameGroup(String nameGroup);
}
