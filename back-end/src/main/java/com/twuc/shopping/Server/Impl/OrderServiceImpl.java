package com.twuc.shopping.Server.Impl;

import com.twuc.shopping.Server.OrderService;
import com.twuc.shopping.domain.Order;
import com.twuc.shopping.po.OrderPO;
import com.twuc.shopping.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll().stream().map(orderPO -> convertOrderPOToOrder(orderPO)).collect(Collectors.toList());
    }


    public Order convertOrderPOToOrder(OrderPO orderPO){
        return  Order.builder()
                .productName(orderPO.getProductName())
                .price(orderPO.getPrice())
                .number(orderPO.getNumber())
                .quantifier(orderPO.getQuantifier())
                .build();
    }
}
