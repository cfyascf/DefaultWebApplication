package com.yasmim.project.impl;

import com.yasmim.project.dto.RegisterData;
import com.yasmim.project.dto.SignResponse;
import com.yasmim.project.entity.UserData;
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

    @Override
    public SignResponse signin(String username, String password) {

        var user = userRepository.findByUsername(username);

        if(user == null) {
            return new SignResponse(
                    null,
                    "User not found.");
        }

        if(!passwordService.verifyEncodedPassword(password, user.getPassword())) {
            return new SignResponse(
                    null,
                    "Wrong password.");
        }

        return new SignResponse(
                user,
                "User signed in successfully.");
    }

    @Override
    public SignResponse signup(RegisterData obj) {

        if(!obj.password().equals(obj.confirmPassword())) {
            return new SignResponse(
                    null,
                    "Passwords do not match.");
        }

        var existingUser = userRepository.findByUsername(obj.username());
        if(existingUser != null) {
            return new SignResponse(
                    null,
                    "Username already exists.");
        }

        if(!passwordService.verifyPasswordStrength(obj.password())) {
            return new SignResponse(
                    null,
                    "Weak password.");
        }

        var department = departmentService.findDepartmentByName(
                obj.department()
        );

        UserData newUser = new UserData();
        newUser.setUsername(format(obj.username()));
        newUser.setFullname(format(obj.fullname()));
        newUser.setEmail(format(obj.email()));
        newUser.setRole(obj.role());
        newUser.setDepartment(department);
        newUser.setPassword(passwordService.encodePassword(obj.confirmPassword()));

        userRepository.save(newUser);

        return new SignResponse(
                newUser,
                "User signed up successfully.");
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
