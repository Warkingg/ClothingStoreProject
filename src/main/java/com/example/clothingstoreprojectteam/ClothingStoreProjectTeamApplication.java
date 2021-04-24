package com.example.clothingstoreprojectteam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
public class ClothingStoreProjectTeamApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClothingStoreProjectTeamApplication.class, args);
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("Messages");
        return messageSource;
    }

}
