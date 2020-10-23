package com.twuc.shopping.controller;

import com.twuc.shopping.service.ProductService;
import com.twuc.shopping.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getProductList(){
        List<Product> products = productService.getProductList();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/product")
    public ResponseEntity saveProduct(@RequestBody @Valid Product product){
        boolean flag = productService.saveProduct(product);
        if (!flag){
            return ResponseEntity.badRequest().body("{\"message\":\"商品已存在\"}");
        }
        return ResponseEntity.created(null).body("{\"message\":\"商品创建成功\"}");
    }
}
