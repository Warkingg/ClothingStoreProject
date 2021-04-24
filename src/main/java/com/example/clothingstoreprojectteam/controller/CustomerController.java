package com.example.clothingstoreprojectteam.controller;

import com.example.clothingstoreprojectteam.model.*;
import com.example.clothingstoreprojectteam.repository.IRoleRepository;
import com.example.clothingstoreprojectteam.service.Customer.ICustomerService;
import com.example.clothingstoreprojectteam.service.Province.IProvinceService;
import com.example.clothingstoreprojectteam.service.Role.IRoleService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;
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

    @ModelAttribute("username")
    public String username(Principal userPrinciple){
        if (userPrinciple!=null){
            return userPrinciple.getName();
        }
        return null;
    }

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

    @GetMapping("/shop")
    public String shop(){
        return "shop";
    }

    @GetMapping("/")
    public ModelAndView home(){
        return new ModelAndView("index");
    }

    @PostMapping("register")
    public ModelAndView register(@Validated @ModelAttribute Customer customer,BindingResult bindingResult){
        new Customer().validate(customer,bindingResult);
        ModelAndView modelAndView;
        if (bindingResult.hasFieldErrors()){
            modelAndView = new ModelAndView("login");
        }else {
            if (iCustomerService.findByUsername(customer.getUsername()) == null) {
                customer.setPassword(passwordEncoder.encode(customer.getPassword()));
                Set<Role> roles = new HashSet<>();
                roles.add(iRoleService.findById(1L).get());
                customer.setRoleSet(roles);
                iCustomerService.save(customer);
                modelAndView = new ModelAndView("shop");
            } else {
                modelAndView = new ModelAndView("login");
            }
        }
      return modelAndView;
    }

    @PostMapping("signIn")
    public String login(@ModelAttribute Customer customer , Model model){
        Customer checkCustomer = iCustomerService.findByUsername(customer.getUsername());
        if (checkCustomer.getUsername().equals(customer.getUsername())){
            if (passwordEncoder.matches(customer.getPassword(),checkCustomer.getPassword())){
               return "redirect:/shop";
            }
        }
           return "login";
    }

    @GetMapping("/user/information")
    public ModelAndView users( @ModelAttribute("username") String username, ModelAndView modelAndView){
            if (username != null) {
                Customer customer = iCustomerService.findByUsername(username);
                modelAndView = new ModelAndView("information");
                modelAndView.addObject("informationCustomer", customer);
                return modelAndView;
            }
            return new ModelAndView("login");
    }
    @PostMapping("user/edit")
    public String edit(@Validated @ModelAttribute Customer customer){
            iCustomerService.save(customer);
            return "redirect:/user/information";
        }
}
