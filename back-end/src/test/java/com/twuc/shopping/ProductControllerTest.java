package com.twuc.shopping;

import com.twuc.shopping.entity.ProductEntity;
import com.twuc.shopping.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
        ProductEntity productEntity1 = ProductEntity.builder()
                .name("可乐")
                .price(2.5)
                .unit("瓶")
                .url("https://xxx")
                .build();

        ProductEntity productEntity2 = ProductEntity.builder()
                .name("雪碧")
                .price(2)
                .unit("瓶")
                .url("https://xxx1")
                .build();

        productRepository.save(productEntity1);
        productRepository.save(productEntity2);
    }

    @Test
    public void should_get_all_product_list() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].productName", is("可乐")))
                .andExpect(jsonPath("$[0].price", is(2.5)))
                .andExpect(jsonPath("$[0].unit", is("瓶")))
                .andExpect(jsonPath("$[0].url", is("https://xxx")))
                .andExpect(jsonPath("$[1].productName", is("雪碧")))
                .andExpect(jsonPath("$[1].price", is(2.0)))
                .andExpect(jsonPath("$[1].unit", is("瓶")))
                .andExpect(jsonPath("$[1].url", is("https://xxx1")));
    }
}
