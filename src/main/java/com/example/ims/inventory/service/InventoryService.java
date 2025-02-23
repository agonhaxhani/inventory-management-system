package com.example.ims.inventory.service;

import com.example.ims.exceptions.BadRequestException;
import com.example.ims.inventory.dto.InventoryResponseDTO;
import com.example.ims.inventory.entity.Inventory;
import com.example.ims.inventory.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<InventoryResponseDTO> getInventory(@PathVariable Long productId, @PathVariable Long subcategoryId) {
        return inventoryRepository.findInventories(productId, subcategoryId).stream().map(InventoryResponseDTO::new).toList();
    }

    public void addToInventory(Long productId, Integer quantity) {
        inventoryRepository.addToInventory(productId, quantity);
    }

    public List<Inventory> findByProductIds(List<Long> productIds) {
        return inventoryRepository.findAllByProductIds(productIds);
    }

    public void removeFromInventory(Long productId, Integer quantity) {
        this.validateInventory(productId, quantity);
        inventoryRepository.removeFromInventory(productId, quantity);
    }

    public void validateInventory(Long productId, Integer quantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new BadRequestException("Inventory not found for product: " + productId));

        if (inventory.getQuantity() < quantity) {
            throw new BadRequestException("Only " + inventory.getQuantity() + " quantity is left for product with ID: " + productId);
        }
    }
}
