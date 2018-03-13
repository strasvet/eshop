package com.example.eshop.controller;


import com.example.eshop.exception.InputValidationException;
import com.example.eshop.model.Product;
import com.example.eshop.model.web.ProductRequest;
import com.example.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/product")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/register")
    public Product register(@RequestBody ProductRequest request, BindingResult result, @RequestHeader("Authorization") String sessionId ){
        if (result.hasErrors()) {
            throw new InputValidationException(result);
        }
        return productService.create(request, sessionId);
    }
    @GetMapping("/{productId}")
    public Product getUserById(@PathVariable("productId") Integer productId) {
        return productService.getById(productId);
    }
    /*@GetMapping("/all")
    public List<Product> findAll(){
        return productService.findAll();
    }*/
    @GetMapping("/all")
    public List<?> findAll(@RequestHeader("Authorization") String sessionId){
        return productService.customAll(sessionId);
    }
}
