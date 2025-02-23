package com.example.ims.category.dto.subcategory;

import com.example.ims.category.dto.category.CategoryDTO;
import com.example.ims.category.entity.Category;
import com.example.ims.category.entity.CategoryType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubcategoryDTO {
    private Long id;
    private String name;
    private CategoryDTO category;
    private CategoryType type;

    public SubcategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.type = category.getCategoryType();
        this.category = new CategoryDTO(category.getParentCategory());
    }
}

