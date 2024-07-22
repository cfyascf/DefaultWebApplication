package com.yasmim.project.services.impl;

import com.yasmim.project.dto.AuthToken;
import com.yasmim.project.dto.JWTPayload;
import com.yasmim.project.dto.LoginData;
import com.yasmim.project.dto.RegisterData;
import com.yasmim.project.entities.UserData;
import com.yasmim.project.entities.VerificationTokenData;
import com.yasmim.project.repositories.VerificationTokenRepository;
import com.yasmim.project.services.EmailService;
import com.yasmim.project.services.exceptions.*;
import com.yasmim.project.repositories.UserRepository;
import com.yasmim.project.services.DepartmentService;
import com.yasmim.project.services.PasswordService;
import com.yasmim.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
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

//        ..verify user existence..
        var user = userRepository.findByUsername(obj.username());
        if(user == null) {
            throw new NotFoundException("User not found.");
        }

//        ..verify if input password is correct..
        if(!passwordService.verifyEncodedPassword(obj.password(), user.getPassword())) {
            throw new UnauthorizedException("Invalid password.");
        }

//        ..checks if user needs a new email verification token..
        var tokens = user.getTokens();
        boolean resend = tokens.isEmpty() ||
                tokens.getFirst().getCreatedAt().before(new Timestamp(System.currentTimeMillis() - (3600000)));

//        ..sends new verification token..
        if(resend) {
            var newToken = jwtService.createVerificationToken(user);
            verificationTokenRepository.save(newToken);
            emailService.sendVerificationEmail(newToken);
        }

        if(!user.getEmailVerified()) {
            throw new UserNotVerifiedException("User must vefiry their email adress.", resend);
        }

//        ..creates jwt..
        var jwt = jwtService.getToken(
                new JWTPayload(user.getUsername(), user.getRole()));

        return new AuthToken(jwt, user.getId(), "User signed in successfully!");
    }

    @Override
    public AuthToken signup(RegisterData obj) {

//        ..password matching verification..
        if(!obj.getPassword().equals(obj.getConfirmPassword())) {
            throw new BadRequestException("Passwords do not match.");
        }

//        ..user existing verification..
        var existingUser = userRepository.findByUsername(obj.getUsername());
        if(existingUser != null) {
            throw new ConflictException("Username already exists.");
        }

//        ..creates new user..
        UserData newUser = new UserData(
                format(obj.getUsername()), format(obj.getFullname()),
                format(obj.getEmail()), obj.getRole(),
                passwordService.encodePassword(obj.getPassword()));

        newUser.setDepartment(departmentService.findDepartmentByName(obj.getDepartment()));

//        ..created their email verification token and sends it by email..
        var verificationToken = jwtService.createVerificationToken(newUser);
        emailService.sendVerificationEmail(verificationToken);
        newUser.getTokens().add(verificationToken);

//        ..saves user and token..
        userRepository.save(newUser);
        verificationTokenRepository.save(verificationToken);

//        ..created jwt..
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
