package com.example.ims.product.entity;

import com.example.ims.category.entity.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name= "product")
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "subcategory_id", nullable = false)
    private Category subcategory;

    @Column(nullable = false)
    private Double price;
}
