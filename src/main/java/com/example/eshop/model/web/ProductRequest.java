package com.example.eshop.model.web;

import com.example.eshop.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRequest {
    private String displayName;
    private String description;
    private Double price;
    private Integer category;
    private User seller;
    //in percents %
    private Integer discount;
    private Integer quantity;
}
