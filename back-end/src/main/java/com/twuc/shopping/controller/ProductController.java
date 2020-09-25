package com.twuc.shopping.controller;

import com.twuc.shopping.Server.ProductService;
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
    public  ResponseEntity saveProduct(@RequestBody @Valid Product product){
        productService.saveProduct(product);
        return ResponseEntity.created(null).build();
    }
}
