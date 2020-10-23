package com.twuc.shopping.api;

import com.twuc.shopping.dto.Product;
import com.twuc.shopping.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProductsList() {
        List<Product> products =  productService.getProductsList();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/product")
    public ResponseEntity addProduct(@RequestBody Product product) {
        productService.createProduct(product);
        return ResponseEntity.ok(null);
    }

}
