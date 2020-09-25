package com.twuc.shopping.Server.Impl;

import com.twuc.shopping.Server.ProductService;
import com.twuc.shopping.domain.Product;
import com.twuc.shopping.po.ProductPO;
import com.twuc.shopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getProductList() {
        return productRepository.findAll()
                .stream()
                .map(productPO -> convertProductPOToProduct(productPO))
                .collect(Collectors.toList());
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(convertProductToProductPO(product));
    }

    public Product convertProductPOToProduct(ProductPO productPO) {
        return Product.builder()
                .productName(productPO.getProductName())
                .price(productPO.getPrice())
                .image(productPO.getImage())
                .quantifier(productPO.getQuantifier())
                .build();
    }

    public ProductPO convertProductToProductPO(Product product){
        return ProductPO.builder()
                .productName(product.getProductName())
                .price(product.getPrice())
                .image(product.getImage())
                .quantifier(product.getQuantifier())
                .build();
    }
}
