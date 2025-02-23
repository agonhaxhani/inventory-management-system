package com.example.ims.inventory.controller;

import com.example.ims.inventory.dto.InventoryResponseDTO;
import com.example.ims.inventory.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping()
    public List<InventoryResponseDTO> getAllInventory(@RequestParam(required = false) Long productId, @RequestParam(required = false) Long subcategoryId) {
        return inventoryService.getInventory(productId, subcategoryId);
    }


    @PostMapping("/product/{productId}/add")
    public void addToInventory(@PathVariable Long productId,
                               @RequestParam int quantity) {
        inventoryService.addToInventory(productId, quantity);
    }

    @PostMapping("/product/{productId}/remove")
    public void removeFromInventory(@PathVariable Long productId,
                                    @RequestParam int quantity) {
        inventoryService.removeFromInventory(productId, quantity);
    }
}
