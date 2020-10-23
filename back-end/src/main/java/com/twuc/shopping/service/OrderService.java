package com.twuc.shopping.service;

import com.twuc.shopping.domain.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrder();

    void save(Order order);
}
