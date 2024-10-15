package motoland.motoland.controllers;

import motoland.motoland.model.Client;
import motoland.motoland.service.ClientService;
import motoland.motoland.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/clienti")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private CommandService comandaService;


    // Get all clients
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    // Get a single client by ID
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new client
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client savedClient = clientService.saveOrUpdateClient(client);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    // Update an existing client
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
        return clientService.getClientById(id)
                .map(existingClient -> {
                    client.setId(id); // Ensure the client ID is not changed.
                    Client updatedClient = clientService.saveOrUpdateClient(client);
                    return new ResponseEntity<>(updatedClient, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a client
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        return clientService.getClientById(id)
                .map(client -> {
                    // Delete or update associated commands before deleting the client
                    comandaService.deleteByClientId(id);  // Ensure you have this method in your service
                    clientService.deleteClient(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Search clients by full name
    @GetMapping("/search")
    public List<Client> findClientsByNumeComplet(@RequestParam String numeComplet) {
        return clientService.findClientsByNumeComplet(numeComplet);
    }
}