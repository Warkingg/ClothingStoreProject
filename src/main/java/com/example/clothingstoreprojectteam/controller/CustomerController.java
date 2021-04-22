package com.example.clothingstoreprojectteam.controller;

import com.example.clothingstoreprojectteam.model.Cart;
import com.example.clothingstoreprojectteam.model.Customer;
import com.example.clothingstoreprojectteam.model.Province;
import com.example.clothingstoreprojectteam.model.Role;
import com.example.clothingstoreprojectteam.repository.IRoleRepository;
import com.example.clothingstoreprojectteam.service.Customer.ICustomerService;
import com.example.clothingstoreprojectteam.service.Province.IProvinceService;
import com.example.clothingstoreprojectteam.service.Role.IRoleService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class CustomerController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ICustomerService iCustomerService;
    @Autowired
    private IProvinceService iProvinceService;
    @Autowired
    private IRoleService iRoleService;

    @ModelAttribute("customer")
    public Customer customer(){
        return new Customer();
    }

    @ModelAttribute("provinces")
    public List<Province> provinces(){
        return (List<Province>) iProvinceService.findAll();
    }

    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @GetMapping("/")
    public ModelAndView home(){
        return new ModelAndView("index");
    }

    @PostMapping("register")
    public ModelAndView register(@ModelAttribute Customer customer){
        ModelAndView modelAndView;
      if (iCustomerService.findByUsername(customer.getUsername())==null){
          customer.setPassword(passwordEncoder.encode(customer.getPassword()));
          Set<Role> roles = new HashSet<>();
          roles.add(iRoleService.findById(1L).get());
          customer.setRoleSet(roles);
          iCustomerService.save(customer);
          modelAndView = new ModelAndView("shop");
      }else {
          modelAndView = new ModelAndView("login");
      }
      return modelAndView;
    }

    @PostMapping("signIn")
    public ModelAndView login(@ModelAttribute Customer customer , ModelAndView modelAndView){
        Customer checkCustomer = iCustomerService.findByUsername(customer.getUsername());
        if (checkCustomer.getUsername().equals(customer.getUsername())){
            if (passwordEncoder.matches(customer.getPassword(),checkCustomer.getPassword())){
                modelAndView = new ModelAndView("shop");
                String message = "Hello "+checkCustomer.getFirstName();
                modelAndView.addObject("message",message);
            }
        }else {
            modelAndView = new ModelAndView("login");
        }
        return modelAndView;
    }
}
