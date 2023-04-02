package com.example.ProjectUniver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewController(ViewControllerRegistry registry){
        registry.addViewController("/home").setViewName("mainPage");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/registrationUser").setViewName("registrationUser");
        registry.addViewController("/registrationOrganization").setViewName("registrationOrganization");
    }
}