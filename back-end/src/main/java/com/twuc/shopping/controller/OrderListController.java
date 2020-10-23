package com.twuc.shopping.controller;

import com.twuc.shopping.domain.OrderList;
import com.twuc.shopping.service.OrderListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderListController {
    @Autowired
    OrderListService orderListService;

    @PostMapping("/orders")
    public ResponseEntity saveOrderList(@RequestBody OrderList orderList){
        orderListService.saveOrderList(orderList);
        return ResponseEntity.created(null).build();
    }
}
