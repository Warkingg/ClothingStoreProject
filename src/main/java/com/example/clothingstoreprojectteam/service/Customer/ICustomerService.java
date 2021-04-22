package com.example.clothingstoreprojectteam.service.Customer;

import com.example.clothingstoreprojectteam.model.Customer;
import com.example.clothingstoreprojectteam.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ICustomerService extends IGeneralService<Customer>, UserDetailsService {
        Customer findByUsername(String username);
}
