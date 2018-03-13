package com.example.eshop.service;

import com.example.eshop.model.Purchase;
import com.example.eshop.model.dtoByRole.PurchaseForNoAdmin;
import com.example.eshop.model.web.PurchaseRequest;

import java.util.List;

public interface PurchaseService {
    PurchaseForNoAdmin create(PurchaseRequest request, String sessionId);
    Purchase getById(Integer id);
    List<Purchase> findAll();
    List<?> findAllCustom(String sessionId);
}
