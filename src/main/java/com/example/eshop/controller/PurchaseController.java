package com.example.eshop.controller;

import com.example.eshop.exception.InputValidationException;
import com.example.eshop.model.Purchase;
import com.example.eshop.model.web.PurchaseRequest;
import com.example.eshop.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/purchase")
@RestController
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/register")
    public Purchase register(@RequestBody PurchaseRequest request, BindingResult result, @RequestHeader("Authorization") String sessionId ){
        if (result.hasErrors()) {
            throw new InputValidationException(result);
        }
        return purchaseService.create(request, sessionId);
    }
    @GetMapping("/{purchaseId}")
    public Purchase getById(@PathVariable("purchaseId") Integer purchaseId) {
        return purchaseService.getById(purchaseId);
    }
    @GetMapping("/all")
    public List<Purchase> findAll(){
        return purchaseService.findAll();
    }
}
