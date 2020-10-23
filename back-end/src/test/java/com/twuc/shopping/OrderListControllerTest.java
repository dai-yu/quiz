package com.twuc.shopping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twuc.shopping.domain.Order;
import com.twuc.shopping.domain.OrderList;
import com.twuc.shopping.po.ProductPO;
import com.twuc.shopping.repository.OrderRepository;
import com.twuc.shopping.repository.ProductRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.LinkedList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderListControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void should_save_order_list() throws Exception{
        ProductPO apple = productRepository.findByProductName("苹果");
        ProductPO sprite = productRepository.findByProductName("雪碧");
        Order order1 = Order.builder().productName(apple.getProductName()).price(apple.getPrice()).number(12).quantifier(apple.getQuantifier()).build();
        Order order2 = Order.builder().productName(sprite.getProductName()).price(sprite.getPrice()).number(12).quantifier(sprite.getQuantifier()).build();
        LinkedList<Order> list=new LinkedList<>();
        list.add(order1);
        list.add(order2);
        OrderList orderList=OrderList.builder().orders(list).build();
        String jsonString = objectMapper.writeValueAsString(orderList);
        mockMvc.perform(post("/orders").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
//        assertEquals();
    }
}
