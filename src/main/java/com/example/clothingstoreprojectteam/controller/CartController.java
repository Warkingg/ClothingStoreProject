package com.example.clothingstoreprojectteam.controller;

import com.example.clothingstoreprojectteam.model.Cart;
import com.example.clothingstoreprojectteam.model.CartItem;
import com.example.clothingstoreprojectteam.model.Category;
import com.example.clothingstoreprojectteam.model.Product;
import com.example.clothingstoreprojectteam.service.cart.CartService;
import com.example.clothingstoreprojectteam.service.category.ICategoryService;
import com.example.clothingstoreprojectteam.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView showCart(){
       return new ModelAndView("/checkout1");
    }

    @GetMapping("/shops")
    public ModelAndView showproduct(){
        return new ModelAndView("/checkout1");
    }

    @ModelAttribute("categories")
    public Iterable<Category> getAllCategory() {
        Iterable<Category> categories = categoryService.findAll();
        return categories;
    }

    @RequestMapping("/add")
    public ModelAndView add(HttpSession session, @RequestParam("id") Long id, @RequestParam(value = "qty" , required = false, defaultValue = "1")int qty){
        Product product = productService.get(id);
        Cart cart =  cartService.getCart(session);
        cart.addItem(product,qty);
        return new ModelAndView("/cart");
    }

    @GetMapping("/remove/{id}")
    public ResponseEntity<Void> remove(HttpSession session, @PathVariable ("id") Long id){
        Product product = productService.get(id);
        Cart cart = cartService.getCart(session);
        cart.removeItem(product);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/update/{id}")
        public ResponseEntity<CartItem> update(@PathVariable("id") Long id, HttpSession session){
        Product product = productService.get(id);
        Cart cart =  cartService.getCart(session);
        CartItem item = cart.getItem(product);
        item.setQuantity(item.getQuantity() + 1);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }


    @GetMapping("/update2/{id}")
    public ResponseEntity<CartItem> update2(@PathVariable("id") Long id, HttpSession session){
        Product product = productService.get(id);
        Cart cart =  cartService.getCart(session);
        CartItem item = cart.getItem(product);
        item.setQuantity(item.getQuantity() - 1);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
