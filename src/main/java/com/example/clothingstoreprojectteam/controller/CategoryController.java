package com.example.clothingstoreprojectteam.controller;

import com.example.clothingstoreprojectteam.model.Category;
import com.example.clothingstoreprojectteam.model.Product;
import com.example.clothingstoreprojectteam.service.category.ICategoryService;

import com.example.clothingstoreprojectteam.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IProductService productService;

    @GetMapping("/list")
    public ModelAndView showAll() {
        Iterable<Category> categories = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView("/category/list");
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createNewProduct(@ModelAttribute Category category) {
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category", new Category());
        modelAndView.addObject("message", "Danh mục mới đã được tạo");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showUpdateForm(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        ModelAndView modelAndView;
        if (categoryOptional.isPresent()) {
            modelAndView = new ModelAndView("/category/edit");
            modelAndView.addObject("category", categoryOptional.get());
        } else {
            modelAndView = new ModelAndView("/error-404");
        }
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView editCategory(@ModelAttribute Category category) {
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/category/edit");
        modelAndView.addObject("category", category);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        ModelAndView modelAndView;
        if (categoryOptional.isPresent()) {
            modelAndView = new ModelAndView("/category/delete");
            modelAndView.addObject("category", categoryOptional.get());
        } else {
            modelAndView = new ModelAndView("/error-404");
        }
        return modelAndView;
    }

    @PostMapping("/delete")
    public String deleteProduct(@ModelAttribute Category category) {
        categoryService.remove(category.getId());
        return "redirect:/categories/list";
    }
    @GetMapping("/{id}/view")
    public ModelAndView showCategoryInformation(@PathVariable("id") Long id,Pageable pageable){
        Optional<Category> categoryOptional = categoryService.findById(id);
        if(!categoryOptional.isPresent()){
            return new ModelAndView("/error-404");
        }
        Page<Product> products = productService.findAllByCategory(categoryOptional.get(),pageable);
        ModelAndView modelAndView = new ModelAndView("/category/view");
        modelAndView.addObject("category",categoryOptional.get());
        modelAndView.addObject("products",products);
        return modelAndView;
    }
}
