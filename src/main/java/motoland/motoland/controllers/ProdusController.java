package motoland.motoland.controllers;

import motoland.motoland.model.Produs;
import motoland.motoland.service.CommandService;
import motoland.motoland.service.ProdusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/produse")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class ProdusController {

    @Autowired
    private ProdusService produsService;

    @Autowired
    private CommandService commandService;

    // Create a new product
    @PostMapping
    public ResponseEntity<Produs> createProdus(@RequestBody Produs produs) {
        Produs savedProdus = produsService.saveProdus(produs);
        return new ResponseEntity<>(savedProdus, HttpStatus.CREATED);
    }

    // Get a product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Produs> getProdusById(@PathVariable Long id) {
        Optional<Produs> produs = produsService.getProdusById(id);
        return produs.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all products
    @GetMapping
    public List<Produs> getAllProduse() {
        return produsService.getAllProduse();
    }

    // Update a product by ID
    @PutMapping("/{id}")
    public ResponseEntity<Produs> updateProdus(@PathVariable Long id, @RequestBody Produs produs) {
        Optional<Produs> existingProdus = produsService.getProdusById(id);

        if (existingProdus.isPresent()) {
            produs.setId(id); // Ensure the product ID is not changed
            Produs updatedProdus = produsService.saveProdus(produs);
            return new ResponseEntity<>(updatedProdus, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProdus(@PathVariable Long id) {
        Optional<Produs> produs = produsService.getProdusById(id);

        if (produs.isPresent()) {
            produsService.deleteProdus(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Find products by status
    @GetMapping("/status")
    public ResponseEntity<List<Produs>> findProduseByStatus(@RequestParam String status) {
        List<Produs> produse = produsService.findProduseByStatus(status);
        if (produse.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(produse, HttpStatus.OK);
    }

    // Find a product by code (unique)
    @GetMapping("/code")
    public ResponseEntity<Produs> findProdusByCodProdus(@RequestParam String codProdus) {
        Produs produs = produsService.findByCodProdus(codProdus);
        return produs != null ? new ResponseEntity<>(produs, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Find products by associated command ID
    @GetMapping("/comanda/{comandaId}")
    public ResponseEntity<List<Produs>> findProduseByComandaId(@PathVariable Long comandaId) {
        List<Produs> produse = produsService.findProduseByComandaId(comandaId);
        if (produse.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(produse, HttpStatus.OK);
    }

    // Update the status of a product by its ID
    @PutMapping("/{productId}/status")
    public ResponseEntity<Void> updateProductStatus(@PathVariable Long productId, @RequestBody Map<String, String> statusUpdate) {
        try {
            String newStatus = statusUpdate.get("status");
            boolean updated = produsService.updateProductStatus(productId, newStatus);
            if (updated) {
                // If the product is part of a command, update the command's status based on its products
                Long commandId = produsService.findCommandIdByProductId(productId); // Find the associated command ID
                boolean commandUpdated = commandService.updateCommandStatus(commandId, "Completa");
                if (commandUpdated) {
                    return ResponseEntity.ok().build(); // Status updated successfully
                } else {
                    return ResponseEntity.notFound().build(); // Command not found
                }
            } else {
                return ResponseEntity.notFound().build(); // Product not found
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Internal server error
        }
    }


}