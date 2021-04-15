package com.example.clothingstoreprojectteam.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="product_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
