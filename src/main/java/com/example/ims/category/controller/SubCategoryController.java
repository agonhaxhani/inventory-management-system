package com.example.ims.category.controller;

import com.example.ims.category.dto.subcategory.SubcategoryDTO;
import com.example.ims.category.dto.subcategory.SubcategoryCreateDTO;
import com.example.ims.category.service.SubCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category/{categoryId}/subcategory")
public class SubCategoryController {
    private final SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @GetMapping()
    public List<SubcategoryDTO> getAllCategories(@PathVariable Long categoryId) {
        return subCategoryService.findAllByCategoryId(categoryId);
    }

    @PostMapping()
    public SubcategoryDTO createSubcategory(@PathVariable Long categoryId, @RequestBody SubcategoryCreateDTO subcategoryCreateDTO) {
        return subCategoryService.insertSubcategory(categoryId, subcategoryCreateDTO);
    }
}
