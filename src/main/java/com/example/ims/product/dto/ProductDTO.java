package com.example.ims.product.dto;

import com.example.ims.product.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private double price;
    private Long subcategoryId;
    private String subcategoryName;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.subcategoryId = product.getSubcategory().getId();
        this.subcategoryName = product.getSubcategory().getName();
    }
}
