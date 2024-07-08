package com.yasmim.project.configuration;

import com.yasmim.project.filter.JWTFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class FiltersConfiguration {

    @Autowired
    JWTFilter jwtFilter;

    @Bean
    @Scope("singleton")
    protected FilterRegistrationBean<JWTFilter> validateToken() {

        FilterRegistrationBean<JWTFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(jwtFilter);
        filter.addUrlPatterns("/*");

        return filter;
    }
}
