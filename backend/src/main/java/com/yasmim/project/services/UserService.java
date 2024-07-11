package com.yasmim.project.services;

import com.yasmim.project.dto.AuthToken;
import com.yasmim.project.dto.LoginData;
import com.yasmim.project.dto.RegisterData;
import com.yasmim.project.entities.UserData;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public AuthToken signin(LoginData obj);
    public AuthToken signup(RegisterData obj);
    public UserData findUserByUsername(String username);
    public UserData findUserByFullName(String fullname);
}
