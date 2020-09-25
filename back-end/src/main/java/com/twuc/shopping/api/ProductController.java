package com.twuc.shopping.api;


import com.twuc.shopping.dto.Product;
import com.twuc.shopping.entity.ProductEntity;
import com.twuc.shopping.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProductsList() {
        List<ProductEntity> allProducts = productRepository.findAll();
        List<Product> products =  allProducts.stream()
                .map(ProductController::mapFromProductEntitiesToProduct).collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    private static Product mapFromProductEntitiesToProduct(ProductEntity productEntity) {
        if (productEntity == null) {
            return null;
        }
        return Product.builder()
                .productName(productEntity.getName())
                .price(productEntity.getPrice())
                .unit(productEntity.getUnit())
                .url(productEntity.getUrl())
                .build();
    }
}