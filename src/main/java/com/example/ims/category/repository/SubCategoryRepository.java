package com.example.ims.category.repository;

import com.example.ims.category.entity.Category;
import com.example.ims.category.entity.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubCategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByParentCategory_Id(Long categoryId);

    Optional<Category> findByIdAndCategoryType(Long categoryId, CategoryType categoryType);
}
