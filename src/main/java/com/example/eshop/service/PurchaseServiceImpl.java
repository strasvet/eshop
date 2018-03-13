package com.example.eshop.service;

import com.example.eshop.exception.InputValidationException;
import com.example.eshop.exception.myn.InsufficiendFundsException;
import com.example.eshop.exception.myn.NotEnoughProductsException;
import com.example.eshop.exception.myn.ProductNotFoundException;
import com.example.eshop.model.Product;
import com.example.eshop.model.Purchase;
import com.example.eshop.model.User;
import com.example.eshop.model.web.PurchaseRequest;
import com.example.eshop.repository.PurchaseRepository;
import com.example.eshop.repository.SessionRepository;
import com.example.eshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PurchaseServiceImpl implements PurchaseService{

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public Purchase create(PurchaseRequest request,String sessionId) {
        //search bought user from session ID
        User boughtBy = sessionRepository.getBySessionId(sessionId).getUser();
        //User boughtBy = userService.findByEmail(request.getBoughtByEmail());//kupil dont by from ne shuschestvueshiy user bez session id

        Product product = productService.getById(request.getProductId());
        if(product==null)throw new ProductNotFoundException("Product with ID [" + request.getProductId() + "] is not found");
        //
        if(product.getSeller().getId().equals(boughtBy.getId())){
            throw new IllegalStateException("NELZYA KUPIT U SAMOGO SEBYA!!!");
        }

        //

        Purchase purchase = new Purchase();
        purchase.setBoughtBy(boughtBy); //pokupatel
        purchase.setSellDate(new Date());

        //prduct get vverhu
        purchase.setSoldBy(product.getSeller()); //prodavec by id product

        Double endPrice = product.getPrice()*product.getDiscount()/100.0;
        purchase.setEndPrice(endPrice);
        purchase.setPrice_without_discount(product.getPrice());
        purchase.setProduct_id(productService.getById(product.getId()));


        //TO DO check quantity
        if(request.getQuantity()>product.getQuantity()) {
            throw new NotEnoughProductsException("Not enought product from seller, you over limit");
            //String errorMessage = String.format("Quantity [%s] from buyer > quantity from seller [%s] ",request.getQuantity(), product.getQuantity());
            //throw new InputValidationException("quantity", errorMessage);
        }
        purchase.setQuantity(request.getQuantity());


        //update quantity seller
        product.setQuantity(product.getQuantity()-request.getQuantity());
        productService.update(product);


        //check balance bought
        //reWrite balance
        Double endSummAll = request.getQuantity()*endPrice;
        if(endSummAll>boughtBy.getBalance()) {
            throw new InsufficiendFundsException("Not enough money, you over limit");
            //String errorMessage = String.format("End Summ of All Product [%s]  > from balance buyer [%s] ",endSummAll, boughtBy.getBalance());
            //throw new InputValidationException("quantity", errorMessage);
        }
        boughtBy.setBalance(boughtBy.getBalance()-endSummAll);
        userService.update(boughtBy);

        //Add money from deal to seller
        User seller = productService.getById(request.getProductId()).getSeller();
        seller.setBalance(seller.getBalance()+endSummAll);

        return purchaseRepository.save(purchase);
    }

    @Override
    public Purchase getById(Integer id) {
        Purchase purchaseFind = purchaseRepository.findById(id).get();
        if(purchaseFind == null) throw new ProductNotFoundException("Purchase with ID [" + id + "] is not found");
        return purchaseFind;
    }

    @Override
    public List<Purchase> findAll() {
        List<Purchase> list = purchaseRepository.findAll();
        if(list.isEmpty()) throw new ProductNotFoundException("Database is empty, purchase not found.");
        return list;
    }
}
