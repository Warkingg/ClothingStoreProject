package com.example.clothingstoreprojectteam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductForm {
    private Long id;

    private String name;

    private double price;

    private int quantity;

    private String description;

    private MultipartFile image;

    private Category category;
}