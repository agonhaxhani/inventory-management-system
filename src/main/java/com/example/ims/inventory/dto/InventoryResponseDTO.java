package com.example.ims.inventory.dto;

import com.example.ims.inventory.entity.Inventory;
import com.example.ims.product.dto.ProductDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryResponseDTO {
    private ProductDTO product;
    private int quantity;

    public InventoryResponseDTO(Inventory inventory) {
        this.product = new ProductDTO(inventory.getProduct());
        this.quantity = inventory.getQuantity();
    }
}
