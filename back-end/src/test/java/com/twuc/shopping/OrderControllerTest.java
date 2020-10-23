package com.twuc.shopping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twuc.shopping.domain.Order;
import com.twuc.shopping.domain.OrderList;
import com.twuc.shopping.po.OrderListPo;
import com.twuc.shopping.po.OrderPO;
import com.twuc.shopping.po.ProductPO;
import com.twuc.shopping.repository.OrderRepository;
import com.twuc.shopping.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

//    @BeforeEach
    public void setUp(){
        ProductPO productPO = ProductPO.builder().productName("可口可乐").quantifier("瓶").image("可口可乐.png").price(3).build();
        productRepository.save(productPO);
        OrderPO orderPO = OrderPO.builder().productPO(productPO).number(12).price(productPO.getPrice()).quantifier(productPO.getQuantifier()).build();
        OrderPO orderPO2 = OrderPO.builder().productPO(productPO).number(1).price(productPO.getPrice()).quantifier(productPO.getQuantifier()).build();
        OrderPO orderPO3 = OrderPO.builder().productPO(productPO).number(3).price(productPO.getPrice()).quantifier(productPO.getQuantifier()).build();
        orderRepository.save(orderPO);
        orderRepository.save(orderPO2);
        orderRepository.save(orderPO3);
    }

//    @AfterEach
    @Transactional
    public void clear(){
        orderRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    public void should_get_all_order_item() throws Exception {
        mockMvc.perform(get("/order"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(3)));
    }


    @Test
    public void should_save_order() throws  Exception{
        ProductPO productPO = ProductPO.builder().productName("可口可乐").quantifier("瓶").image("可口可乐.png").price(3).build();
        Order order = Order.builder().productName(productPO.getProductName()).quantifier(productPO.getQuantifier()).number(12).price(productPO.getPrice()).build();
        String jsonString = objectMapper.writeValueAsString(order);
        mockMvc.perform(post("/order").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        assertEquals(4,orderRepository.count());
    }

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
