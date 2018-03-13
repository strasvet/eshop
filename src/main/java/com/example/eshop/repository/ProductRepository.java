package com.example.eshop.repository;

import com.example.eshop.model.Product;
import com.example.eshop.model.User;
import com.example.eshop.model.dtoByRole.ProductForNoAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByDisplayName(String name);
    Product findByDisplayNameAndSeller(String displayname, User seller);

    @Query("select new com.example.eshop.model.dtoByRole.ProductForNoAdmin(p.displayName,p.description,p.id,p.price,p.quantity,p.category,p.seller.firstName,p.seller.lastName) from Product p")
    List<ProductForNoAdmin> getProductForNoAdmin();

}
