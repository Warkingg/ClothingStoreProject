package com.example.clothingstoreprojectteam.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double price;

    private int quantity;

    private String size;

    private String description;

    private String imgUrl;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;


}
