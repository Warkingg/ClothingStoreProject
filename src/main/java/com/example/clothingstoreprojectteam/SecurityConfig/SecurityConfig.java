package com.example.clothingstoreprojectteam.SecurityConfig;

import com.example.clothingstoreprojectteam.service.Customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ICustomerService iCustomerService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(iCustomerService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().disable();
        http.authorizeRequests().antMatchers("/signIn","/","/register","/login").permitAll()
                .antMatchers("/css/**","/js/**","/images/**","/fonts/**","/img/**").permitAll()
                .antMatchers("/user**","/product/**").hasRole("USER")
                .antMatchers("/admin**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").successForwardUrl("/signIn").permitAll()
                .and().logout().logoutSuccessUrl("/")
                .and().exceptionHandling().accessDeniedPage("/404-error");
    }
}
