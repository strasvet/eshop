package com.example.eshop.controller;

import com.example.eshop.exception.InputValidationException;
import com.example.eshop.model.Category;
import com.example.eshop.model.web.CategoryRequest;
import com.example.eshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/category")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public Category register(@RequestBody CategoryRequest request, BindingResult result){
        if (result.hasErrors()) {
            throw new InputValidationException(result);
        }
        return categoryService.create(request);
    }
    @GetMapping("/all")
    public List<Category> findAll(){
        return categoryService.findAll();
    }
}
