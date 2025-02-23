package com.example.ims.inventory.repository;

import com.example.ims.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByProductId(Long productId);

    @Query("""
    SELECT i FROM inventory i
    JOIN i.product p
    JOIN p.subcategory c
    WHERE (:productId IS NULL OR p.id = :productId)
    AND (:subcategoryId IS NULL OR c.id = :subcategoryId)
""")
    List<Inventory> findInventories(Long productId, Long subcategoryId);

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO inventory (product_id, quantity)
        VALUES (:productId, :amount)
        ON CONFLICT (product_id) DO UPDATE
        SET quantity = inventory.quantity + EXCLUDED.quantity
        """, nativeQuery = true)
    void addToInventory(Long productId, Integer amount);


    @Modifying
    @Transactional
    @Query("UPDATE inventory i SET i.quantity = i.quantity - :quantity WHERE i.product.id = :productId")
    void removeFromInventory(Long productId, Integer quantity);

    @Query("""
        SELECT i FROM inventory i WHERE i.product.id IN :productIds
    """)
    List<Inventory> findAllByProductIds(List<Long> productIds);
}

