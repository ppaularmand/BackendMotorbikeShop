package motoland.motoland.repositories;

import motoland.motoland.model.Motorcycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotorcycleRepository extends JpaRepository<Motorcycle, Long> {
    // Custom query to find by motorcycle brand
    List<Motorcycle> findByMarca(String marca);

    // Custom query to find by serial number (unique field)
    Motorcycle findBySerieSasiu(String serieSasiu);
}