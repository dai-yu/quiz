package com.twuc.shopping.controller;

import com.twuc.shopping.Server.OrderService;
import com.twuc.shopping.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/order")
    public void getOrders(){
        List<Order> orders=orderService.getAllOrder();
    }
}
