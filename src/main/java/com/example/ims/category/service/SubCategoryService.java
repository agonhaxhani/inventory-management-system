package com.example.ims.category.service;

import com.example.ims.category.dto.category.CategoryDTO;
import com.example.ims.category.dto.subcategory.SubcategoryDTO;
import com.example.ims.category.dto.subcategory.SubcategoryCreateDTO;
import com.example.ims.category.entity.Category;
import com.example.ims.category.entity.CategoryType;
import com.example.ims.category.repository.SubCategoryRepository;
import com.example.ims.exceptions.BadRequestException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryService {
    private final SubCategoryRepository subCategoryRepository;
    private final CategoryService categoryService;

    public SubCategoryService(SubCategoryRepository subCategoryRepository,
                              CategoryService categoryService) {
        this.subCategoryRepository = subCategoryRepository;
        this.categoryService = categoryService;
    }

    public List<SubcategoryDTO> findAllByCategoryId(Long categoryId) {
        return subCategoryRepository.findAllByParentCategory_Id(categoryId).stream()
                .map(SubcategoryDTO::new)
                .toList();
    }

    public CategoryDTO getSubcategoryById(Long id) {
        Category category = subCategoryRepository.findByIdAndCategoryType(id, CategoryType.SUBCATEGORY)
                .orElseThrow(() -> new BadRequestException("Subcategory not found"));
        return new CategoryDTO(category);
    }

    public SubcategoryDTO insertSubcategory(Long categoryId, SubcategoryCreateDTO subcategoryCreateDTO) {
        CategoryDTO parentCategory = categoryService.getCategoryById(categoryId);

        Category category = new Category();
        category.setName(subcategoryCreateDTO.getName());
        category.setParentCategory(new Category(parentCategory.getId(), parentCategory.getName()));
        category.setCategoryType(CategoryType.SUBCATEGORY);

        return insert(category);
    }

    public SubcategoryDTO insert(@Valid Category category) {
        Category cat = subCategoryRepository.save(category);
        return new SubcategoryDTO(cat);
    }
}
