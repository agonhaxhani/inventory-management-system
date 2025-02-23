package com.example.ims.product.controller;


import com.example.ims.product.dto.ProductCreateDTO;
import com.example.ims.product.dto.ProductDTO;
import com.example.ims.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<ProductDTO> getAllProducts(@RequestParam(required = false) Long subcategoryId) {
        return productService.getAllSubcategoryProducts(subcategoryId);
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductCreateDTO productCreateDTO) {
        return ResponseEntity.ok(productService.createProduct(productCreateDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductCreateDTO productCreateDTO) {
        return ResponseEntity.ok(productService.updateProduct(id, productCreateDTO));
    }
}
