package com.yasmim.project.impl;

import com.yasmim.project.service.PasswordService;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class BCryptPasswordService implements PasswordService {

    @Override
    public Boolean verifyPasswordStrength(String password) {
        
        if(password.length() < 8) {
            return false;
        } else if(password.chars().noneMatch(Character::isUpperCase)) {
            return false;
        } else if(password.chars().noneMatch(Character::isLowerCase)) {
            return false;
        } else if(password.chars().noneMatch(Character::isDigit)) {
            return false;
        }

        return true;
    }

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
