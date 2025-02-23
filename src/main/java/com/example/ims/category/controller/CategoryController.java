package com.example.ims.category.controller;

import com.example.ims.category.dto.category.CategoryCreateDTO;
import com.example.ims.category.dto.category.CategoryDTO;
import com.example.ims.category.entity.Category;
import com.example.ims.category.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping()
    public Category createCategory(@RequestBody CategoryCreateDTO categoryCreateDTO) {
        return categoryService.insertCategory(categoryCreateDTO);
    }

    @GetMapping()
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryDTO getCategory(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }
}
