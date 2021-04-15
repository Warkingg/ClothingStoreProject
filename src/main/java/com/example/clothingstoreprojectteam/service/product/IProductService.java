package com.example.clothingstoreprojectteam.service.product;

import com.example.clothingstoreprojectteam.model.Product;
import com.example.clothingstoreprojectteam.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService extends IGeneralService<Product> {
    Page<Product> findAllByNameContaining(String name, Pageable pageable);
    Page<Product> findAllProductByNameUsingQuery(String name,Pageable pageable);
}
