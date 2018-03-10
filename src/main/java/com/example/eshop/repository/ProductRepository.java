package com.example.eshop.repository;

import com.example.eshop.model.Product;
import com.example.eshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByDisplayName(String name);
    Product findByDisplayNameAndSeller(String displayname, User seller);

}
