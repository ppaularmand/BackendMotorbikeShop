package motoland.motoland.repositories;


import motoland.motoland.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    // Find clients by full name
    List<Client> findByNumeComplet(String numeComplet);

    // Find clients by telephone number
    List<Client> findByTelefon(String telefon);

    // Find clients by motorcycle id
    List<Client> findByIdMotocicleta(Long idMotocicleta);

    // Custom query to find clients by CNP
    @Query("SELECT c FROM Client c WHERE c.cnp = ?1")
    List<Client> findByCnp(String cnp);

    // Custom query to search clients by partial address
    @Query("SELECT c FROM Client c WHERE c.adresa LIKE %?1%")
    List<Client> findByAdresaContaining(String adresa);
}