package com.twuc.shopping.Server;

import com.twuc.shopping.domain.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrder();
}