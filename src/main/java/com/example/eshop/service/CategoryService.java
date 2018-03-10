package com.example.eshop.service;

import com.example.eshop.model.Category;
import com.example.eshop.model.web.CategoryRequest;

import java.util.List;

public interface CategoryService {
    Category create(CategoryRequest request);
    Category findById(Integer id);
    Category findByName(String name);
    List<Category> findAll();
}
