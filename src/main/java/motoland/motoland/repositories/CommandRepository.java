package motoland.motoland.repositories;

import motoland.motoland.model.Comanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandRepository extends JpaRepository<Comanda, Long> {

    // Find all commands by client ID
    List<Comanda> findByClientId(Long clientId);

    @Query("SELECT c.id FROM Comanda c JOIN c.produse p WHERE p.id = :productId")
    Long findCommandIdByProductId(@Param("productId") Long productId);

    // Inside your CommandRepository
    @Query("SELECT c FROM Comanda c WHERE c.status != 'Anulata' AND c.status != 'Completa'")
    List<Comanda> findActiveCommands();
}