package com.estudos.first_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.first_api.domain.product.Product;
import com.estudos.first_api.domain.product.ProductRepository;
import com.estudos.first_api.domain.product.RequestProduct;
import com.estudos.first_api.response.ApiResponse;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping()
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
        var allProducts = productRepository.findAll();
        ApiResponse<List<Product>> response = new ApiResponse<>(200, "Products found", allProducts);
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<Product>> registerProduct(@RequestBody @Validated RequestProduct data) {
        Product newProduct = new Product(data);
        productRepository.save(newProduct);
        ApiResponse<Product> response = new ApiResponse<>(201, "Products registred successfully", newProduct);
        return ResponseEntity.ok(response);
    }

    @PutMapping()
    public ResponseEntity<ApiResponse<Product>> updateProduct(@RequestBody @Validated RequestProduct data) {
        Product product = productRepository.findById(data.id())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Product newProduct = new Product(data);
        product = newProduct;
        productRepository.save(product);
        ApiResponse<Product> response = new ApiResponse<>(200, "Products actualized", product);
        return ResponseEntity.ok(response);
    }
}
