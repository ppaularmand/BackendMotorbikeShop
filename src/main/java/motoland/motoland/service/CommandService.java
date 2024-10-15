package motoland.motoland.service;

import motoland.motoland.model.Comanda;
import motoland.motoland.model.Produs;
import motoland.motoland.repositories.CommandRepository;
import motoland.motoland.repositories.ProdusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandService {

    @Autowired
    private CommandRepository commandRepository;

    @Autowired
    private ProdusRepository produsRepository;


    public List<Comanda> getAllCommands() {
        return commandRepository.findAll();
    }

    // Save a command with associated products
    public Comanda saveCommand(Comanda comanda) {
        // Save the command first, without products
        Comanda savedCommand = commandRepository.save(comanda);

        // Save each product associated with the command
        for (Produs produs : comanda.getProduse()) {
            produsRepository.save(produs);  // Make sure all fields are correctly saved here
            savedCommand.addProdus(produs); // Add product to the command
        }

        // Save the command again with its associated products
        return commandRepository.save(savedCommand);
    }

    public Optional<Comanda> getCommandById(Long id) {
        return commandRepository.findById(id);
    }

    public List<Comanda> getCommandsByClientId(Long clientId) {
        return commandRepository.findByClientId(clientId);
    }

    public void deleteCommand(Long id) {
        commandRepository.deleteById(id);
    }

    public void updateCommandStatusBasedOnProducts(Long commandId) {
        List<Produs> products = produsRepository.findByComandaId(commandId);

        // Check if all products are 'Compact'
        boolean allCompact = products.stream().allMatch(prod -> "Compact".equals(prod.getStatus()));

        Optional<Comanda> optionalCommand = commandRepository.findById(commandId);
        if (optionalCommand.isPresent()) {
            Comanda command = optionalCommand.get();
            if (allCompact) {
                command.setStatus("Completa"); // Set to Completa if all products are Compact
            } else {
                command.setStatus("Incompleta"); // Otherwise, set to Incompleta
            }
            commandRepository.save(command); // Save the updated command status
        }
    }

    // Method to update the command status
    public boolean updateCommandStatus(Long commandId, String newStatus) {
        Optional<Comanda> optionalCommand = commandRepository.findById(commandId);
        if (optionalCommand.isPresent()) {
            Comanda command = optionalCommand.get();
            command.setStatus(newStatus); // Update the status
            commandRepository.save(command); // Save the updated command
            return true; // Update successful
        } else {
            return false; // Command not found
        }
    }

    public void deleteByClientId(Long clientId) {
        List<Comanda> commands = commandRepository.findByClientId(clientId);
        for (Comanda command : commands) {
            commandRepository.delete(command);
        }
    }
}