package com.twuc.shopping.service;

import com.twuc.shopping.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProductList();

    boolean saveProduct(Product product);
}
