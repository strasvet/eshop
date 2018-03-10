package com.example.eshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String displayName;

    private String description;

    private Double price;

    private Integer category;
    //seller: -> reference "user
    //@ManyToMany
    @ManyToOne
    @JoinColumn(name="SELLER")
    private User seller;
    //in percents %
    private Integer discount;

    private Integer quantity;
}
