package com.example.ims.product.service;

import com.example.ims.category.dto.category.CategoryDTO;
import com.example.ims.category.entity.Category;
import com.example.ims.category.service.SubCategoryService;
import com.example.ims.exceptions.BadRequestException;
import com.example.ims.product.dto.ProductCreateDTO;
import com.example.ims.product.dto.ProductDTO;
import com.example.ims.product.entity.Product;
import com.example.ims.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final SubCategoryService subCategoryService;

    public ProductService(ProductRepository productRepository,
                          SubCategoryService subCategoryService) {
        this.productRepository = productRepository;
        this.subCategoryService = subCategoryService;
    }

    private Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Product not found"));
    }

    public List<ProductDTO> getAllSubcategoryProducts(Long subcategoryId) {
        if (subcategoryId != null) {
            return productRepository.findBySubcategoryId(subcategoryId).stream()
                    .map(ProductDTO::new)
                    .toList();
        }

        return getAllProducts();
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductDTO::new)
                .toList();
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new BadRequestException("This product does not exist!"));
        return new ProductDTO(product);
    }

    public ProductDTO createProduct(ProductCreateDTO productCreateDTO) {
        CategoryDTO category = subCategoryService.getSubcategoryById(productCreateDTO.getSubcategoryId());

        Product product = new Product();
        product.setName(productCreateDTO.getName());
        product.setPrice(productCreateDTO.getPrice());
        product.setSubcategory(new Category(category.getId(), category.getName()));

        return new ProductDTO(productRepository.save(product));
    }


    public ProductDTO updateProduct(Long id, ProductCreateDTO productCreateDTO) {
        Product product = this.findById(id);
        CategoryDTO category = subCategoryService.getSubcategoryById(productCreateDTO.getSubcategoryId());

        product.setName(productCreateDTO.getName());
        product.setPrice(productCreateDTO.getPrice());
        product.setSubcategory(new Category(category.getId(), category.getName()));

        return new ProductDTO(productRepository.save(product));
    }

}
