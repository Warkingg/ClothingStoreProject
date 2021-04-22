package com.example.clothingstoreprojectteam.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


public class Cart {
    private  final List<CartItem> items;
    private  float total;
    public Cart (){
        items = new ArrayList<CartItem>();
        total = 0;
    }

    public  CartItem getItem(Product p){
        for (CartItem item :items){
            if (item.getProduct().getId() == p.getId()){
                return item;
            }
        }
        return null;
    }
    public List<CartItem> getItems(){
        return items;
    }
    public int getItemCount(){
        return items.size();
    }

    public void addItem(CartItem item){
        addItem(item.getProduct(), item.getQuantity());
    }
//add item
    public void addItem(Product p , int quantity){
        CartItem item = getItem(p);
        if (item!= null){
            item.setQuantity(item.getQuantity()+quantity);
        }else {
            item = new CartItem(p);
            item.setQuantity(quantity);
            items.add(item);
        }
    }

    //sua sp
    public void updateItem(Product p , int quantity){
        CartItem item = getItem(p);
        if(item!= null){
            item.setQuantity(quantity);
        }
    }

    //xoa sp
    public void removeItem(Product p ){
        CartItem item = getItem(p);
        if(item!= null){
            items.remove(item);
        }
    }






}
