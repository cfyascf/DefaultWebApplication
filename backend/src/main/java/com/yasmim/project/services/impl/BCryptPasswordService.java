package com.yasmim.project.services.impl;

import com.yasmim.project.services.PasswordService;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class BCryptPasswordService implements PasswordService {

    @Override
    public String encodePassword(String password) {
        return BCrypt.hashpw(
            password, 
            BCrypt.gensalt()
        );
    }

    @Override
    public Boolean verifyEncodedPassword(String rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }
}
