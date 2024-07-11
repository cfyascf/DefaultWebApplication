package com.yasmim.project.controllers;

import com.yasmim.project.dto.AuthToken;
import com.yasmim.project.dto.LoginData;
import com.yasmim.project.dto.RegisterData;
import com.yasmim.project.services.JWTService;
import com.yasmim.project.services.UserService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<AuthToken> signup(
            @Valid @RequestBody RegisterData obj) {

        jwtService.verifyPermission(0);

        return ResponseEntity.ok(userService.signup(obj));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthToken> login(
            @RequestBody LoginData obj) {

        return ResponseEntity.ok(userService.signin(obj));
    }
}
