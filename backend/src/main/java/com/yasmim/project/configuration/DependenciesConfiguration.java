package com.yasmim.project.configuration;

import com.yasmim.project.impl.*;
import com.yasmim.project.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class DependenciesConfiguration {

    @Bean
    @Scope("singleton")
    public UserService userService() {
        return new DefaultUserService();
    }

    @Bean
    @Scope("singleton")
    public DepartmentService departmentService() {
        return new DefaultDepartmentService();
    }

    @Bean
    @Scope("singleton")
    public ServiceService serviceService() {
        return new DefaultServiceService();
    }

    @Bean
    @Scope("singleton")
    public PasswordService passwordService() {
        return new BCryptPasswordService();
    }

    @Bean
    @Scope("singleton")
    public JWTService jwtService() {
        return new RSASHA256JWTService();
    }

    @Bean
    @Scope("singleton")
    public KeyService keyService() {
        return new RSAKeyService();
    }
}