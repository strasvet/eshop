package com.example.eshop.service;

import com.example.eshop.exception.InputValidationException;
import com.example.eshop.exception.UserNotFoundException;
import com.example.eshop.exception.myn.ProductNotFoundException;
import com.example.eshop.model.Category;
import com.example.eshop.model.Product;
import com.example.eshop.model.User;
import com.example.eshop.model.web.ProductRequest;
import com.example.eshop.repository.CategoryRepository;
import com.example.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;
    //private CategoryRepository categoryRepository;
    @Autowired
    private UserService userService;

    @Override
    public Product create(ProductRequest request) {
        //Product findProduct = productRepository.findByDisplayName(request.getDisplayName());
        /*if(findProduct != null){
            String errorMessage = String.format("Product [%s] is already present in the system", request.getDisplayName());
            throw new InputValidationException("product", errorMessage);
        }*/
        Product findProduct = productRepository.findByDisplayNameAndSeller(request.getDisplayName(), userService.findByEmail(request.getSeller().getEmail()));
        if(findProduct != null){
            String errorMessage = String.format("Product [%s] is already present in the system", request.getDisplayName());
            throw new InputValidationException("product", errorMessage);
        }
        Product product = new Product();
        if(categoryService.findById(request.getCategory()) == null) {
            String errorMessage = String.format("Category [%s] is not found in the system", request.getCategory());
            throw new InputValidationException("category", errorMessage);
        }
        product.setCategory(request.getCategory());
        product.setDescription(request.getDescription());
        product.setDiscount(request.getDiscount());
        product.setDisplayName(request.getDisplayName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        User seller =  userService.findByEmail(request.getSeller().getEmail());
        if(seller == null){
            String errorMessage = String.format("Seller [%s] is not found in the system", request.getSeller().getEmail());
            throw new InputValidationException("seller", errorMessage);
        }
        product.setSeller(seller);
        return productRepository.save(product);
    }

    @Override
    public Product getById(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Product with ID [" + id + "] not found"));
    }

    @Override
    public List<Product> findAll() {
        List<Product> list = productRepository.findAll();
        if(list.isEmpty()) throw new ProductNotFoundException("Database is empty, products not found.");
        return list;
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }
}
