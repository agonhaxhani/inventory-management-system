package com.example.ims.category.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name= "category")
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryType categoryType;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> subcategories;

    @AssertTrue(message = "If type is SUBCATEGORY, parentCategory must be set. If type is CATEGORY, parentCategory must be null.")
    private boolean isValidParent() {
        if (categoryType == CategoryType.CATEGORY) {
            return parentCategory == null;
        } else {
            return parentCategory != null;
        }
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}