package com.example.clothingstoreprojectteam.model;

import javax.persistence.*;
import java.util.List;

public class CartItem {
private  final  Product product;
private  int  quantity;
private float subTotal;

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getSubTotal() {
        subTotal = product.getPrice() * quantity;
        return quantity;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "item_product",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @ManyToOne
    private Cart cart;

    public CartItem(Product product)  {
        this.product = product;
        this.quantity = 1;
        this.subTotal = product.getPrice();
    }
}
