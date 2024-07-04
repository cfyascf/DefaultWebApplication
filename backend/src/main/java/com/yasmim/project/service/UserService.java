package com.yasmim.project.service;

import com.yasmim.project.dto.LoginData;
import com.yasmim.project.dto.RegisterData;
import com.yasmim.project.dto.SignResponse;
import com.yasmim.project.entity.UserData;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public String signin(LoginData obj);
    public String signup(RegisterData obj);
    public UserData findUserByUsername(String username);
    public UserData findUserByFullName(String fullname);
}
