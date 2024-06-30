package com.yasmim.project.service;

import com.yasmim.project.dto.RegisterData;
import com.yasmim.project.dto.SignResponse;
import com.yasmim.project.entity.UserData;

public interface UserService {
    public SignResponse signin(String username, String password);
    public SignResponse signup(RegisterData user);
    public UserData findUserByUsername(String username);
    public UserData findUserByFullName(String fullname);
}
