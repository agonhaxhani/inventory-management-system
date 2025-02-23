package com.example.ims.category.dto.category;

import com.example.ims.category.entity.Category;
import com.example.ims.category.entity.CategoryType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CategoryDTO {
    private Long id;
    private String name;
    private CategoryType type;
    private List<CategorySubCategoryDTO> subCategories;

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.type = category.getCategoryType();

        if (category.getSubcategories() != null && !category.getSubcategories().isEmpty()) {
            this.subCategories = category.getSubcategories().stream()
                    .map(CategorySubCategoryDTO::new)
                    .collect(Collectors.toList());
        }
    }
}

