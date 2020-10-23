package com.twuc.shopping.service;

import com.twuc.shopping.api.ProductController;
import com.twuc.shopping.dto.Product;
import com.twuc.shopping.entity.ProductEntity;
import com.twuc.shopping.exception.ProductAlreadyExistsException;
import com.twuc.shopping.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductsList() {
        List<ProductEntity> allProducts = productRepository.findAll();
        List<Product> products =  allProducts.stream()
                .map(ProductService::mapFromProductEntitiesToProduct).collect(Collectors.toList());
        return products;
    }

    public void createProduct(Product product) {
        if (productRepository.existsByName(product.getName())) {
            throw new ProductAlreadyExistsException("商品已存在");
        }
        ProductEntity productEntity = ProductEntity.builder()
                .name(product.getName())
                .price(product.getPrice())
                .unit(product.getUnit())
                .url(product.getUrl())
                .build();
        productRepository.save(productEntity);
    }

    private static Product mapFromProductEntitiesToProduct(ProductEntity productEntity) {
        if (productEntity == null) {
            return null;
        }
        return Product.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .unit(productEntity.getUnit())
                .url(productEntity.getUrl())
                .build();
    }
}
