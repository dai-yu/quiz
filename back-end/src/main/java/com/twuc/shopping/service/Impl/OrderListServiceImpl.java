package com.twuc.shopping.service.Impl;

import com.twuc.shopping.domain.Order;
import com.twuc.shopping.domain.OrderList;
import com.twuc.shopping.po.OrderListPo;
import com.twuc.shopping.po.OrderPO;
import com.twuc.shopping.po.ProductPO;
import com.twuc.shopping.repository.OrderListRepository;
import com.twuc.shopping.repository.OrderRepository;
import com.twuc.shopping.repository.ProductRepository;
import com.twuc.shopping.service.OrderListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class OrderListServiceImpl implements OrderListService {
    @Autowired
    OrderListRepository orderListRepository;

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public boolean saveOrderList(OrderList orderList) {
        OrderListPo orderListPo = OrderListPo.builder().build();
//        orderListRepository.save(orderListPo);
        orderListPo.setOrders(orderList.getOrders().stream().map(order -> convertOrderToOrderPO(order,orderListPo)).collect(Collectors.toList()));
        orderListRepository.save(orderListPo);
        return true;
    }

    public Order convertOrderPOToOrder(OrderPO orderPO){
        return  Order.builder()
                .productName(orderPO.getProductPO().getProductName())
                .price(orderPO.getPrice())
                .number(orderPO.getNumber())
                .quantifier(orderPO.getQuantifier())
                .build();
    }

    public OrderPO convertOrderToOrderPO(Order order,OrderListPo orderListPo){
        ProductPO productPO = productRepository.findByProductName(order.getProductName());
        OrderPO orderPO = OrderPO.builder()
                .productPO(productPO)
                .quantifier(order.getQuantifier())
                .price(order.getPrice())
                .number(order.getNumber())
                .orderList(orderListPo)
                .build();
//        orderRepository.save(orderPO);
        return orderPO;
    }
}
