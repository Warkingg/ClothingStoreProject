package com.example.clothingstoreprojectteam.repository;

import com.example.clothingstoreprojectteam.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
