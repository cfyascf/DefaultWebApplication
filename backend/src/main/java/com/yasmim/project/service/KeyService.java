package com.yasmim.project.service;

import org.springframework.stereotype.Service;

import java.security.KeyPair;

@Service
public interface KeyService {
    public KeyPair getKeys();
}
