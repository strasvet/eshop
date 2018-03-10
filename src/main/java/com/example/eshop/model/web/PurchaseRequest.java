package com.example.eshop.model.web;

import com.example.eshop.model.Product;
import com.example.eshop.model.User;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PurchaseRequest {
    private Integer productId;
    private String boughtByEmail;
    private Integer quantity;
    /*
    private User soldBy;
    private User boughtBy;
    private Date sellDate;
    private Double endPrice;
    private Double price_without_discount;
    private Product product_id;
    private Integer quantity;*/
}
