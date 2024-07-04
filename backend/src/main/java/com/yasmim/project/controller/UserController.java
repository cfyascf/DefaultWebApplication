package com.yasmim.project.controller;

import com.yasmim.project.dto.LoginData;
import com.yasmim.project.dto.AuthToken;
import com.yasmim.project.dto.JWTPayload;
import com.yasmim.project.dto.RegisterData;
import com.yasmim.project.service.JWTService;
import com.yasmim.project.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(
            @RequestBody RegisterData obj,
            @RequestHeader("Authorization") String jwt) {

        jwtService.verifyPermission(jwt, 0);

        return ResponseEntity.ok(userService.signup(obj));
    }

    @PostMapping("/signin")
    public ResponseEntity<String> login(
            @RequestBody LoginData obj) {

        return ResponseEntity.ok(userService.signin(obj));
    }
}
