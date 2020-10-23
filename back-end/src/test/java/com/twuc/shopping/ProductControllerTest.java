package com.twuc.shopping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twuc.shopping.dto.Product;
import com.twuc.shopping.entity.ProductEntity;
import com.twuc.shopping.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
    }

    @Test
    public void should_get_all_product_list() throws Exception {
        ProductEntity productEntity1 = ProductEntity.builder()
                .name("可乐")
                .price(2.5)
                .unit("瓶")
                .url("https://xxx")
                .build();

        ProductEntity productEntity2 = ProductEntity.builder()
                .name("雪碧")
                .price(2.0)
                .unit("瓶")
                .url("https://xxx1")
                .build();

        productRepository.save(productEntity1);
        productRepository.save(productEntity2);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("可乐")))
                .andExpect(jsonPath("$[0].price", is(2.5)))
                .andExpect(jsonPath("$[0].unit", is("瓶")))
                .andExpect(jsonPath("$[0].url", is("https://xxx")))
                .andExpect(jsonPath("$[1].name", is("雪碧")))
                .andExpect(jsonPath("$[1].price", is(2.0)))
                .andExpect(jsonPath("$[1].unit", is("瓶")))
                .andExpect(jsonPath("$[1].url", is("https://xxx1")));
    }

    @Test
    public void should_add_a_product() throws Exception {
        Product product = Product.builder()
                .name("芬达")
                .unit("瓶")
                .price(1.5)
                .url("https://xxx2")
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(product);

        mockMvc.perform(post("/product")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        List<ProductEntity> allProducts = productRepository.findAll();
        ProductEntity lastProduct = allProducts.get(allProducts.size() - 1) ;

        Assertions.assertEquals(1, allProducts.size());
        Assertions.assertEquals("芬达", lastProduct.getName());
        Assertions.assertEquals("瓶", lastProduct.getUnit());
        Assertions.assertEquals(1.5, lastProduct.getPrice());
        Assertions.assertEquals("https://xxx2", lastProduct.getUrl());
    }

    @Test
    public void should_not_add_product_when_product_is_exist() throws Exception {
        ProductEntity productEntity1 = ProductEntity.builder()
                .name("可乐")
                .price(2.5)
                .unit("瓶")
                .url("https://xxx")
                .build();

        productRepository.save(productEntity1);

        Product product = Product.builder()
                .name("可乐")
                .unit("瓶")
                .price(3.5)
                .url("https://xxx2")
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(product);

        mockMvc.perform(post("/product")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
}
