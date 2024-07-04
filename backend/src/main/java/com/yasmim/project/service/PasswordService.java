package com.yasmim.project.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public interface PasswordService {
    public Boolean verifyPasswordStrength(String password);
   public String encodePassword(String password);
   public Boolean verifyEncodedPassword(String rawPassword, String encodedPassword);
}
