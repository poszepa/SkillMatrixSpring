package pl.skillmatrix.skillmatrixspringboot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.skillmatrix.skillmatrixspringboot.model.TeamsInWarehouse;

@Repository
public interface TeamsInWarehouseRepository extends JpaRepository<TeamsInWarehouse, Integer> {
    TeamsInWarehouse findByNameTeam(String teamName);
}
