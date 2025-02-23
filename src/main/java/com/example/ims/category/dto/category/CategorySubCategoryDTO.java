package com.example.ims.category.dto.category;

import com.example.ims.category.entity.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategorySubCategoryDTO {
    private Long id;
    private String name;

    public CategorySubCategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}

