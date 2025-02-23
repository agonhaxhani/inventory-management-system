package com.example.ims.product.repository;

import com.example.ims.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = """
            SELECT *
            FROM product p
            WHERE p.subcategory_id = :subcategoryId
        """, nativeQuery = true)
    List<Product> findBySubcategoryId(Long subcategoryId);
}
