package motoland.motoland.service;

import motoland.motoland.model.Produs;
import motoland.motoland.repositories.ProdusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdusService {

    @Autowired
    private ProdusRepository produsRepository;

    // Save or update a product
    public Produs saveProdus(Produs produs) {
        // Ensure all fields are set before saving
        if (produs.getCodProdus() == null || produs.getDenumire() == null ||
                produs.getNrBucati() == null || produs.getPret() == null ||
                produs.getStatus() == null) {
            throw new IllegalArgumentException("All product fields must be completed.");
        }

        return produsRepository.save(produs);
    }

    // Get all products
    public List<Produs> getAllProduse() {
        return produsRepository.findAll();
    }

    // Get a product by ID
    public Optional<Produs> getProdusById(Long id) {
        return produsRepository.findById(id);
    }

    // Delete a product by ID
    public void deleteProdus(Long id) {
        produsRepository.deleteById(id);
    }

    // Find a product by product code
    public Produs findByCodProdus(String codProdus) {
        return produsRepository.findByCodProdus(codProdus);
    }

    // Find products by status
    public List<Produs> findProduseByStatus(String status) {
        return produsRepository.findByStatus(status);
    }

    // Find products by command ID
    public List<Produs> findProduseByComandaId(Long comandaId) {
        return produsRepository.findByComandaId(comandaId); // Updated to use the JPQL query method
    }

    // Update product status by product ID
    public boolean updateProductStatus(Long productId, String newStatus) {
        Optional<Produs> optionalProdus = produsRepository.findById(productId);
        if (optionalProdus.isPresent()) {
            Produs produs = optionalProdus.get();
            produs.setStatus(newStatus); // Update the status
            produsRepository.save(produs); // Save the updated product
            return true;
        }
        return false; // Product not found
    }

    // Find the associated command ID for a given product
    public Long findCommandIdByProductId(Long productId) {
        return produsRepository.findCommandIdByProductId(productId); // Call the repository method
    }
}