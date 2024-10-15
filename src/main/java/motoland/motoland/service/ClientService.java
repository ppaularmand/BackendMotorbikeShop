package motoland.motoland.service;

import motoland.motoland.model.Client;
import motoland.motoland.model.Comanda;
import motoland.motoland.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // Get all clients
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Get a single client by ID
    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    // Create or update a client
    public Client saveOrUpdateClient(Client client) {
        return clientRepository.save(client);
    }

    // Delete a client
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    // Find clients by full name
    public List<Client> findClientsByNumeComplet(String numeComplet) {
        return clientRepository.findByNumeComplet(numeComplet);
    }

    // Find clients by telephone
    public List<Client> findClientsByTelefon(String telefon) {
        return clientRepository.findByTelefon(telefon);
    }

    // Find clients by motorcycle id
    public List<Client> findClientsByIdMotocicleta(Long idMotocicleta) {
        return clientRepository.findByIdMotocicleta(idMotocicleta);
    }

    // Find clients by CNP
    public List<Client> findClientsByCnp(String cnp) {
        return clientRepository.findByCnp(cnp);
    }

    // Search clients by partial address
    public List<Client> searchClientsByAdresa(String adresa) {
        return clientRepository.findByAdresaContaining(adresa);
    }
}
