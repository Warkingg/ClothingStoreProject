package com.example.clothingstoreprojectteam.controller;

import com.example.clothingstoreprojectteam.model.Category;
import com.example.clothingstoreprojectteam.model.Product;
import com.example.clothingstoreprojectteam.model.ProductForm;
import com.example.clothingstoreprojectteam.service.category.ICategoryService;
import com.example.clothingstoreprojectteam.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @Value(value = "${upload.path")
    private String fileUpload;

    @ModelAttribute("categories")
    public Iterable<Category> getAllCategory() {
        Iterable<Category> categories = categoryService.findAll();
        return categories;
    }


    @GetMapping("/list")
    public ModelAndView showAll() {
        Iterable<Product> products = productService.findAll();
        ModelAndView modelAndView = new ModelAndView("product/list");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new ProductForm());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createNewProduct(@ModelAttribute("product") ProductForm productForm) {
        MultipartFile multipartFile = productForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(productForm.getImage().getBytes(), new File(this.fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Product product = new Product();
        product.setName(productForm.getName());
        product.setPrice(productForm.getPrice());
        product.setQuantity(productForm.getQuantity());
        product.setDescription(productForm.getDescription());
        product.setImgUrl(fileName);
        product.setCategory(productForm.getCategory());
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("message", "Sản phẩm mới đã được tạo");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        ModelAndView modelAndView;
        if (productOptional.isPresent()) {
            modelAndView = new ModelAndView("/product/edit");
            Product product = productOptional.get();
            ProductForm productForm = new ProductForm(product.getId(), product.getName(), product.getPrice(), product.getQuantity(), product.getDescription(), null, product.getCategory());
            Iterable<Category> categories = categoryService.findAll();
            modelAndView.addObject("categories", categories);
            modelAndView.addObject("product", productForm);
        } else {
            modelAndView = new ModelAndView("/error-404");
        }
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView updateProduct(@ModelAttribute ProductForm productForm) {
        Product product = new Product();
        MultipartFile multipartFile = productForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(productForm.getImage().getBytes(), new File(this.fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        product.setId(productForm.getId());
        product.setName(productForm.getName());
        product.setPrice(productForm.getPrice());
        product.setQuantity(productForm.getQuantity());
        product.setDescription(productForm.getDescription());
        product.setImgUrl(fileName);
        product.setCategory(productForm.getCategory());
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("product/edit");
        modelAndView.addObject("product", productForm);
        modelAndView.addObject("message", "Sản phẩm đã được cập nhật");
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        ModelAndView modelAndView;
        if (productOptional.isPresent()) {
            modelAndView = new ModelAndView("/product/delete");
            modelAndView.addObject("product", productOptional.get());
        } else {
            modelAndView = new ModelAndView("/error-404");
        }
        return modelAndView;
    }

    @PostMapping("/delete")
    public String deleteProduct(@ModelAttribute Product product) {
        productService.remove(product.getId());
        return "redirect:/products/list";
    }

}



