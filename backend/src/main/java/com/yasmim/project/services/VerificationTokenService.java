package com.yasmim.project.services;

import org.springframework.stereotype.Service;

@Service
public interface VerificationTokenService {
    public String createVerificationToken(String email);
}
