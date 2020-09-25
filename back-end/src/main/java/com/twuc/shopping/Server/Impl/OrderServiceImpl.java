package com.twuc.shopping.Server.Impl;

import com.twuc.shopping.Server.OrderService;
import com.twuc.shopping.domain.Order;
import com.twuc.shopping.po.OrderPO;
import com.twuc.shopping.po.ProductPO;
import com.twuc.shopping.repository.OrderRepository;
import com.twuc.shopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll().stream().map(orderPO -> convertOrderPOToOrder(orderPO)).collect(Collectors.toList());
    }

    @Override
    public void save(Order order) {
        orderRepository.save(convertOrderToOrderPO(order));
    }


    public Order convertOrderPOToOrder(OrderPO orderPO){
        return  Order.builder()
                .productName(orderPO.getProductPO().getProductName())
                .price(orderPO.getPrice())
                .number(orderPO.getNumber())
                .quantifier(orderPO.getQuantifier())
                .build();
    }

    public OrderPO convertOrderToOrderPO(Order order){
        ProductPO productPO = productRepository.findByProductName(order.getProductName());
        return  OrderPO.builder()
                .productPO(productPO)
                .quantifier(order.getQuantifier())
                .price(order.getPrice())
                .number(order.getNumber())
                .build();
    }
}
