package com.twuc.shopping.controller;

import com.twuc.shopping.Server.OrderService;
import com.twuc.shopping.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/order")
    public ResponseEntity<List<Order>> getOrders(){
        List<Order> orders=orderService.getAllOrder();
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/order")
    public ResponseEntity saveOrder(@RequestBody @Valid Order order){
        orderService.save(order);
        return ResponseEntity.created(null).build();
    }
}
