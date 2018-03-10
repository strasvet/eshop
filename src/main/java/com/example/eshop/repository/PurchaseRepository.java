package com.example.eshop.repository;

import com.example.eshop.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase ,Integer > {
}
