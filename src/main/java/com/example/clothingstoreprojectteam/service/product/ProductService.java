package com.example.clothingstoreprojectteam.service.product;

import com.example.clothingstoreprojectteam.model.Category;
import com.example.clothingstoreprojectteam.model.Product;
import com.example.clothingstoreprojectteam.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }


    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> findAllByNameContaining(String name, Pageable pageable) {
        return productRepository.findAllByNameContaining(name,pageable);
    }

    @Override
    public Page<Product> findAllProductByNameUsingQuery(String name, Pageable pageable) {
        return productRepository.findAllProductByNameUsingQuery(name,pageable);
    }

    @Override
    public Page<Product> findAllByCategory(Category category, Pageable pageable) {
        return productRepository.findAllByCategory(category, pageable);
    }

<<<<<<< HEAD
    @Override
    public Product get(Long id) {
        return productRepository.findById(id).get();
    }
=======
>>>>>>> 740c9f28d40a4c6c58372c78575f34dfcffed89c

}


