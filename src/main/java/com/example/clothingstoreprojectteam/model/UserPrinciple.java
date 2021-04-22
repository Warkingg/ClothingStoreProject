package com.example.clothingstoreprojectteam.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class UserPrinciple implements UserDetails {
    private Customer customer;

    private   Collection<? extends GrantedAuthority> roles;

    public UserPrinciple() {
        
    }

    public UserPrinciple(Customer customer, Collection<? extends GrantedAuthority> roles) {
        this.customer = customer;
        this.roles = roles;
    }

    public UserPrinciple build(Customer customer){
        List<GrantedAuthority> authorities = new ArrayList<>();
        Set<Role> roles = customer.getRoleSet();
        for (Role role:roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new UserPrinciple(customer,authorities);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return customer.getPassword();
    }

    @Override
    public String getUsername() {
        return customer.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
