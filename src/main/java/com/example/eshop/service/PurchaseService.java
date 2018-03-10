package com.example.eshop.service;

import com.example.eshop.model.Purchase;
import com.example.eshop.model.web.PurchaseRequest;

import java.util.List;

public interface PurchaseService {
    Purchase create(PurchaseRequest request);
    Purchase getById(Integer id);
    List<Purchase> findAll();
}
