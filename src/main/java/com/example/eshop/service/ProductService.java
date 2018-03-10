package com.example.eshop.service;

import com.example.eshop.model.Product;
import com.example.eshop.model.web.ProductRequest;

import java.util.List;

public interface ProductService {
    Product create(ProductRequest request);

    Product getById(Integer id);

    List<Product> findAll();
    Product update(Product product);
}
