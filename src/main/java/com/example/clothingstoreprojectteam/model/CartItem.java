package com.example.clothingstoreprojectteam.model;

import javax.persistence.*;
import java.util.List;

public class Item {
    private double price;
//    private String sessionId;
    private int quantity;
    private String status;
    private String image;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "item_product",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> product;

    @ManyToOne
    private Cart cart;
}
