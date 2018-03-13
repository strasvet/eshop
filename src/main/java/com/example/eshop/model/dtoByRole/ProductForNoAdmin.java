package com.example.eshop.model.dtoByRole;


import com.example.eshop.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductForNoAdmin {
    private String displayName;
    private String description;
    private Integer thisProductIdForBuyer;
    private Double price;
    private Integer quantity;
    private Integer category;
    private String sellerFirstName;
    private String sellerLastName;
}
