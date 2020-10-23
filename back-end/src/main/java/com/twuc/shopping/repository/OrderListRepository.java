package com.twuc.shopping.repository;

import com.twuc.shopping.po.OrderListPo;
import org.springframework.data.repository.CrudRepository;

public interface OrderListRepository extends CrudRepository<OrderListPo,Integer> {
}
