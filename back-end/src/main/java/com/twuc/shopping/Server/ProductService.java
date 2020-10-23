package com.twuc.shopping.Server;

import com.twuc.shopping.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProductList();

    boolean saveProduct(Product product);
}
