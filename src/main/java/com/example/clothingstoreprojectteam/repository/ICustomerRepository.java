package com.example.clothingstoreprojectteam.repository;

import com.example.clothingstoreprojectteam.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Long> {
        Customer findByUsername(String userName);
}
