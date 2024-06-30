package com.yasmim.project.service;

import com.yasmim.project.dto.RegisterData;
import com.yasmim.project.dto.SignResponse;

public interface UserService {
    public SignResponse signin(String username, String password);
    public SignResponse signup(RegisterData user);
}
