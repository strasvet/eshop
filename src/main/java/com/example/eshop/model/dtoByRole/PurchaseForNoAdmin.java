package com.example.eshop.model.dtoByRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
@Builder
public class PurchaseForNoAdmin {

    private Integer purchaseId;
    private Integer productId;
    private Integer quantity;
    private String soldByFirstName;
    private String soldByLaststName;
    private String boughtByFirstName;
    private String boughtByLaststName;
    private Date sellDate;
    private Double endPrice;
}
