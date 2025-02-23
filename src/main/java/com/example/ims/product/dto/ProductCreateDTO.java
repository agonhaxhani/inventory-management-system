package com.example.ims.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCreateDTO {
    private String name;
    private double price;
    private Long subcategoryId;
}

