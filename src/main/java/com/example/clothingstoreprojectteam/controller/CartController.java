package com.example.clothingstoreprojectteam.controller;

import com.example.clothingstoreprojectteam.model.Cart;
import com.example.clothingstoreprojectteam.model.CartItem;
import com.example.clothingstoreprojectteam.model.Product;
import com.example.clothingstoreprojectteam.service.cart.CartService;
import com.example.clothingstoreprojectteam.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;

    @GetMapping("/add")
    public String add(HttpSession session, @RequestParam("id") Long id, @RequestParam(value = "qty" , required = false, defaultValue = "1")int qty){
        Product product = productService.get(id);
        Cart cart =  cartService.getCart(session);
        cart.removeItem(product);
        return "cart";
    }
}
