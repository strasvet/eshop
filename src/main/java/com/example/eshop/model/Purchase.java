package com.example.eshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PURCHASE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="SOLDBY")
    private User soldBy;


    @ManyToOne
    @JoinColumn(name="BOUGHTBY")
    private User boughtBy;

    private Date sellDate;

    private Double endPrice;

    private Double price_without_discount;

    //@ManyToOne
    @ManyToOne
    @JoinColumn(name = "PRODUCTID")
    private Product product_id;

    private Integer quantity;

}
