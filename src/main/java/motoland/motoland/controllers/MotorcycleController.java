package motoland.motoland.controllers;

import motoland.motoland.model.Motorcycle;
import motoland.motoland.service.MotorcycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/motociclete")
public class MotorcycleController {

    @Autowired
    private MotorcycleService motorcycleService;

    // Get all motorcycles
    @GetMapping
    public List<Motorcycle> getAllMotorcycles() {
        return motorcycleService.getAllMotorcycles();
    }

    // Create a new motorcycle
    @PostMapping
    public ResponseEntity<Motorcycle> createMotorcycle(@RequestBody Motorcycle motorcycle) {
        Motorcycle savedMotorcycle = motorcycleService.saveMotorcycle(motorcycle);
        return new ResponseEntity<>(savedMotorcycle, HttpStatus.CREATED);
    }

    // Get a single motorcycle by ID
    @GetMapping("/{id}")
    public ResponseEntity<Motorcycle> getMotorcycleById(@PathVariable Long id) {
        return motorcycleService.getMotorcycleById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a motorcycle
    @PutMapping("/{id}")
    public ResponseEntity<Motorcycle> updateMotorcycle(@PathVariable Long id, @RequestBody Motorcycle motorcycle) {
        return motorcycleService.getMotorcycleById(id)
                .map(existingMotorcycle -> {
                    motorcycle.setId(id); // Ensure the ID is set correctly
                    Motorcycle updatedMotorcycle = motorcycleService.saveMotorcycle(motorcycle);
                    return new ResponseEntity<>(updatedMotorcycle, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a motorcycle
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMotorcycle(@PathVariable Long id) {
        motorcycleService.deleteMotorcycle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}