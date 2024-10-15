package motoland.motoland.service;

import motoland.motoland.model.Motorcycle;
import motoland.motoland.repositories.MotorcycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotorcycleService {

    @Autowired
    private MotorcycleRepository motorcycleRepository;

    // Save or update a motorcycle
    public Motorcycle saveMotorcycle(Motorcycle motorcycle) {
        return motorcycleRepository.save(motorcycle);
    }

    // Get all motorcycles
    public List<Motorcycle> getAllMotorcycles() {
        return motorcycleRepository.findAll();
    }

    // Get a motorcycle by ID
    public Optional<Motorcycle> getMotorcycleById(Long id) {
        return motorcycleRepository.findById(id);
    }

    // Delete a motorcycle by ID
    public void deleteMotorcycle(Long id) {
        motorcycleRepository.deleteById(id);
    }

    // Find motorcycles by brand
    public List<Motorcycle> findMotorcyclesByMarca(String marca) {
        return motorcycleRepository.findByMarca(marca);
    }

    // Find motorcycle by serial number (unique)
    public Motorcycle findMotorcycleBySerieSasiu(String serieSasiu) {
        return motorcycleRepository.findBySerieSasiu(serieSasiu);
    }
}
