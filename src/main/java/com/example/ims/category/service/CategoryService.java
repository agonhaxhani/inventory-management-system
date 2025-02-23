package com.example.ims.category.service;

import com.example.ims.category.dto.category.CategoryCreateDTO;
import com.example.ims.category.dto.category.CategoryDTO;
import com.example.ims.category.entity.Category;
import com.example.ims.category.entity.CategoryType;
import com.example.ims.category.repository.CategoryRepository;
import com.example.ims.exceptions.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService  {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findByCategoryType(CategoryType.CATEGORY).stream()
                .map(CategoryDTO::new)
                .toList();
    }

    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findByIdAndCategoryType(id, CategoryType.CATEGORY)
                .orElseThrow(() -> new BadRequestException("Category not found"));
        return new CategoryDTO(category);
    }

    public Category insertCategory(CategoryCreateDTO categoryCreateDTO) {
        Category category = new Category();
        category.setName(categoryCreateDTO.getName());
        category.setCategoryType(CategoryType.CATEGORY);

        return categoryRepository.save(category);
    }
}
