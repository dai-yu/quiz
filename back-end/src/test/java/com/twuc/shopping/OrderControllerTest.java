package com.twuc.shopping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twuc.shopping.po.OrderPO;
import com.twuc.shopping.po.ProductPO;
import com.twuc.shopping.repository.OrderRepository;
import com.twuc.shopping.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void setUp(){
//        ProductPO productPO = ProductPO.builder().productName("可口可乐").quantifier("瓶").image("可口可乐.png").price(3).build();
//        productRepository.save(productPO);
//        OrderPO orderPO = OrderPO.builder().productName(productPO.getProductName()).number(12).price(productPO.getPrice()).quantifier(productPO.getQuantifier()).build();
//        OrderPO orderPO2 = OrderPO.builder().productName(productPO.getProductName()).number(1).price(productPO.getPrice()).quantifier(productPO.getQuantifier()).build();
//        OrderPO orderPO3 = OrderPO.builder().productName(productPO.getProductName()).number(3).price(productPO.getPrice()).quantifier(productPO.getQuantifier()).build();
//        orderRepository.save(orderPO);
//        orderRepository.save(orderPO2);
//        orderRepository.save(orderPO3);
    }

    @AfterEach
    @Transactional
    public void clear(){
        orderRepository.deleteAll();
    }
    @Test
    public void should_get_all_order_item() throws Exception {
        mockMvc.perform(get("/order"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(3)));
    }
}
