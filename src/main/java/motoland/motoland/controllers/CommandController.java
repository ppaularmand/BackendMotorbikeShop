package motoland.motoland.controllers;

import motoland.motoland.model.Comanda;
import motoland.motoland.repositories.CommandRepository;
import motoland.motoland.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RequestMapping("/comenzi")
public class CommandController {

    @Autowired
    private CommandService commandService;

    @Autowired
    private CommandRepository commandRepository;


    @GetMapping
    public List<Comanda> getAllCommands() {
        return commandService.getAllCommands();
    }

    // Get all commands for a specific client
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Comanda>> getCommandsByClientId(@PathVariable Long clientId) {
        try {
            System.out.println("Fetching commands for clientId: " + clientId);

            List<Comanda> commands = commandService.getCommandsByClientId(clientId);

            // Check if the command list is empty or null
            if (commands == null || commands.isEmpty()) {
                System.out.println("No commands found for clientId: " + clientId);
                return ResponseEntity.noContent().build(); // 204 No Content
            }

            // Log the commands for debugging
            commands.forEach(command -> System.out.println("Found command: " + command.getId()));

            return ResponseEntity.ok(commands); // 200 OK
        } catch (Exception e) {
            System.err.println("Error fetching commands for clientId: " + clientId);
            e.printStackTrace(); // Print stack trace to identify the error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    // Create a new command and associate products with it
    @PostMapping
    public ResponseEntity<Comanda> createCommand(@RequestBody Comanda comanda) {
        Comanda newCommand = commandService.saveCommand(comanda);
        return new ResponseEntity<>(newCommand, HttpStatus.CREATED); // Returns 201 Created
    }

    // Update an existing command and its products
    @PutMapping("/{commandId}")
    public ResponseEntity<Comanda> updateCommand(@PathVariable Long commandId, @RequestBody Comanda comanda) {
        Optional<Comanda> existingCommand = commandService.getCommandById(commandId);
        if (existingCommand.isPresent()) {
            comanda.setId(commandId); // Ensure the command ID is set correctly
            Comanda updatedCommand = commandService.saveCommand(comanda);
            return new ResponseEntity<>(updatedCommand, HttpStatus.OK); // Returns 200 OK
        } else {
            return ResponseEntity.notFound().build(); // Returns 404 Not Found if command not found
        }
    }

    // Delete a command and dissociate products from it
    @DeleteMapping("/{commandId}")
    public ResponseEntity<Void> deleteCommand(@PathVariable Long commandId) {
        Optional<Comanda> command = commandService.getCommandById(commandId);
        if (command.isPresent()) {
            commandService.deleteCommand(commandId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Returns 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Returns 404 Not Found if command not found
        }
    }

    @PutMapping("/{commandId}/status")
    public ResponseEntity<Void> updateCommandStatus(@PathVariable Long commandId, @RequestBody Map<String, String> statusUpdate) {
        try {
            String newStatus = statusUpdate.get("status");
            boolean updated = commandService.updateCommandStatus(commandId, newStatus);
            if (updated) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Command not found
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Internal server error
        }
    }

    @GetMapping("/active")
    public List<Comanda> getActiveCommands() {
        return commandRepository.findActiveCommands();
    }
}