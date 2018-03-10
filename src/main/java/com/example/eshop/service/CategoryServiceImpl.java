package com.example.eshop.service;

import com.example.eshop.exception.InputValidationException;
import com.example.eshop.exception.myn.ProductNotFoundException;
import com.example.eshop.model.Category;
import com.example.eshop.model.web.CategoryRequest;
import com.example.eshop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category create(CategoryRequest request) {
        Category findCategory = categoryRepository.findByName(request.getName());
        if(findCategory !=null){
            String errorMessage = String.format("Category [%s] is already present in the system", request.getName());
            throw new InputValidationException("category", errorMessage);
        }
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(Integer id) {
        Category category = categoryRepository.findById(id).get();
        return category;
    }

    @Override
    public Category findByName(String name) {
        Category category = categoryRepository.findByName(name);
        if(category ==null){
            String errorMessage = String.format("Category [%s] is not found in the system", name);
            throw new InputValidationException("category", errorMessage);
        }
        return category;
    }

    @Override
    public List<Category> findAll() {
        List<Category> list = categoryRepository.findAll();
        if(list.isEmpty()) throw new ProductNotFoundException("Database is empty, category not found.");
        return list;
    }
}
