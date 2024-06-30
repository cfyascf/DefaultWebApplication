package com.yasmim.project.impl;

import com.yasmim.project.service.PasswordService;

public class DefaultPasswordService implements PasswordService {

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
}
