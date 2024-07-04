package com.yasmim.project.impl;

import com.yasmim.project.dto.JWTPayload;
import com.yasmim.project.dto.LoginData;
import com.yasmim.project.dto.RegisterData;
import com.yasmim.project.dto.SignResponse;
import com.yasmim.project.entity.UserData;
import com.yasmim.project.exception.BadRequestException;
import com.yasmim.project.exception.ConflictException;
import com.yasmim.project.exception.NotFoundException;
import com.yasmim.project.exception.UnauthorizedException;
import com.yasmim.project.repository.UserRepository;
import com.yasmim.project.service.DepartmentService;
import com.yasmim.project.service.PasswordService;
import com.yasmim.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;

public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private RSASHA256JWTService jwtService;

    @Override
    public String signin(LoginData obj) {

        var user = userRepository.findByUsername(obj.username());
        if(user == null) {
            throw new NotFoundException("User not found");
        }

        if(!passwordService.verifyEncodedPassword(obj.password(), user.getPassword())) {
            throw new UnauthorizedException("Invalid password");
        }

        return jwtService.getToken(new JWTPayload(user.getUsername(), user.getRole()));
    }

    @Override
    public String signup(RegisterData obj) {

        if(!obj.password().equals(obj.confirmPassword())) {
            throw new BadRequestException("Passwords do not match");
        }

        if(!passwordService.verifyPasswordStrength(obj.password())) {
            throw new BadRequestException("Weak password.");
        }

        var existingUser = userRepository.findByUsername(obj.username());
        if(existingUser != null) {
            throw new ConflictException("Username already exists");
        }

        var department = departmentService.findDepartmentByName(obj.department());

        UserData newUser = new UserData();
        newUser.setUsername(format(obj.username()));
        newUser.setFullname(format(obj.fullname()));
        newUser.setEmail(format(obj.email()));
        newUser.setRole(obj.role());
        newUser.setDepartment(department);
        newUser.setPassword(passwordService.encodePassword(obj.confirmPassword()));

        userRepository.save(newUser);

        return jwtService.getToken(new JWTPayload(newUser.getUsername(), newUser.getRole()));
    }

    @Override
    public UserData findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserData findUserByFullName(String fullname) {
        return userRepository.findByFullname(fullname);
    }

    public String format(String input) {
        return input.toLowerCase(Locale.ROOT);
    }
}
