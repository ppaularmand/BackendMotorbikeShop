package motoland.motoland.repositories;

import motoland.motoland.model.Produs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdusRepository extends JpaRepository<Produs, Long> {

    // Find products by product code (unique field)
    Produs findByCodProdus(String codProdus);

    // Find products by status
    List<Produs> findByStatus(String status);

    // Find products by their associated command (comanda)
    // Correct method to find products by command ID using the Many-to-Many relationship
    @Query("SELECT p FROM Produs p JOIN p.comenzi c WHERE c.id = :comandaId")
    List<Produs> findByComandaId(@Param("comandaId") Long comandaId);


    @Query("SELECT c.id FROM Comanda c JOIN c.produse p WHERE p.id = :productId")
    Long findCommandIdByProductId(@Param("productId") Long productId);

}