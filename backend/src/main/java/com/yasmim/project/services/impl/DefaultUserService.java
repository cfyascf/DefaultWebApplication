package com.yasmim.project.services.impl;

import com.yasmim.project.dto.AuthToken;
import com.yasmim.project.dto.JWTPayload;
import com.yasmim.project.dto.LoginData;
import com.yasmim.project.dto.RegisterData;
import com.yasmim.project.entities.UserData;
import com.yasmim.project.entities.VerificationTokenData;
import com.yasmim.project.repositories.VerificationTokenRepository;
import com.yasmim.project.services.EmailService;
import com.yasmim.project.services.exceptions.BadRequestException;
import com.yasmim.project.services.exceptions.ConflictException;
import com.yasmim.project.services.exceptions.NotFoundException;
import com.yasmim.project.services.exceptions.UnauthorizedException;
import com.yasmim.project.repositories.UserRepository;
import com.yasmim.project.services.DepartmentService;
import com.yasmim.project.services.PasswordService;
import com.yasmim.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;

public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private RSASHA256JWTService jwtService;

    @Autowired
    private EmailService emailService;

    @Override
    public AuthToken signin(LoginData obj) {

        var user = userRepository.findByUsername(obj.username());
        if(user == null) {
            throw new NotFoundException("User not found.");
        }

        if(!passwordService.verifyEncodedPassword(obj.password(), user.getPassword())) {
            throw new UnauthorizedException("Invalid password.");
        }

        var jwt = jwtService.getToken(
                new JWTPayload(user.getUsername(), user.getRole()));

        return new AuthToken(jwt, user.getId(), "User signed in successfully!");
    }

    @Override
    public AuthToken signup(RegisterData obj) {

        if(!obj.getPassword().equals(obj.getConfirmPassword())) {
            throw new BadRequestException("Passwords do not match.");
        }

        var existingUser = userRepository.findByUsername(obj.getUsername());
        if(existingUser != null) {
            throw new ConflictException("Username already exists.");
        }

        var department = departmentService.findDepartmentByName(obj.getDepartment());

        UserData newUser = new UserData();
        newUser.setUsername(format(obj.getUsername()));
        newUser.setFullname(format(obj.getFullname()));
        newUser.setEmail(format(obj.getEmail()));
        newUser.setRole(obj.getRole());
        newUser.setDepartment(department);
        newUser.setPassword(passwordService.encodePassword(obj.getPassword()));

        var verificationToken = emailService.createVerificationToken(newUser);
        emailService.sendVerificationEmail(verificationToken);
        newUser.getTokens().add(verificationToken);

        userRepository.save(newUser);
        verificationTokenRepository.save(verificationToken);

        var jwt = jwtService.getToken(
                new JWTPayload(newUser.getUsername(), newUser.getRole()));

        return new AuthToken(jwt, newUser.getId(), "User signed up successfully!");
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
