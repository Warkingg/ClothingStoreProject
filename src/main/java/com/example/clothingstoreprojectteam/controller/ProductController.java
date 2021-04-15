package com.example.clothingstoreprojectteam.controller;

import com.example.clothingstoreprojectteam.model.Product;
import com.example.clothingstoreprojectteam.service.product.IProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
public class ProductController  {
    @Autowired
    private IProductService productService;
    @GetMapping("/list")
    public ModelAndView showAll(){
        Iterable<Product> products = productService.findAll();
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products", products);
        return modelAndView;

    }
}
