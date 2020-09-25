package com.twuc.shopping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twuc.shopping.po.ProductPO;
import com.twuc.shopping.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    @Transactional
    public void setUp(){
        ProductPO productPO = ProductPO.builder().productName("可口可乐").quantifier("瓶").image("可口可乐.png").price(3).build();
        ProductPO productPO2 = ProductPO.builder().productName("雪碧").quantifier("瓶").image("雪碧.png").price(3).build();
        ProductPO productPO3 = ProductPO.builder().productName("苹果").quantifier("斤").image("苹果.png").price(5).build();
        ProductPO productPO4 = ProductPO.builder().productName("奥利奥").quantifier("盒").image("奥利奥.png").price(15).build();
        ProductPO productPO5 = ProductPO.builder().productName("电池").quantifier("对").image("电池.png").price(2).build();
        ProductPO productPO6 = ProductPO.builder().productName("方便面").quantifier("包").image("方便面.png").price(4).build();
        productRepository.save(productPO);
        productRepository.save(productPO2);
        productRepository.save(productPO3);
        productRepository.save(productPO4);
        productRepository.save(productPO5);
        productRepository.save(productPO6);
    }

    @AfterEach
    @Transactional
    public void clear(){
        productRepository.deleteAll();
    }
    @Test
    public void should_get_all_products_when_in_homepage() throws Exception {
        Long size= productRepository.count();
        mockMvc.perform(get("/product"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(size.intValue())));
    }

    @Test
    public void should_save_product() throws Exception{
        ProductPO productPO = ProductPO.builder().productName("香蕉").quantifier("斤").image("香蕉.png").price(6).build();
        String jsonString = objectMapper.writeValueAsString(productPO);
        mockMvc.perform(post("/product").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        assertEquals(6,productRepository.findByProductName("香蕉").getPrice());
        assertEquals("斤",productRepository.findByProductName("香蕉").getQuantifier());
    }
}
