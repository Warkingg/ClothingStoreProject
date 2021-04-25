package com.example.clothingstoreprojectteam.model;

import lombok.Data;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name="customers")
public class Customer implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;


    private String gender;

    @Column(unique = true)
    private String username;

    private String password;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "customer_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roleSet;

    @ManyToOne
    @JoinColumn(name = "province_Id")
    private Province province;

    @Override
    public boolean supports(Class<?> clazz) {
        return Customer.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Customer customer = (Customer) target;
        String username = customer.getUsername();
        String password = customer.getPassword();
        if (username.length()>30||username.length()<3){
            errors.rejectValue("username","username.empty");
        }else if (!username.matches("^[A-Z]+[a-z0-9]*$")){
            errors.rejectValue("username","username.startsWith");
        }else if (password.length()>12||password.length()<3){
            errors.rejectValue("password","password.length");
        }else if (!password.matches("^[a-zA-Z0-9]*$")){
            errors.rejectValue("password","password.startsWith");
        }
    }
}
