package com.example.ims.inventory.entity;

import com.example.ims.product.entity.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Entity(name= "inventory")
@Getter
@Setter
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false, unique = true)
    private Product product;
}
