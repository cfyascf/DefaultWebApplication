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
    public ResponseEntity<AuthToken> signup(
            @RequestBody RegisterData obj,
            @RequestHeader("Authorization") String jwt) {

        if(!jwtService.verifyPermission(jwt, 0)) {
            return new ResponseEntity<>(
                    new AuthToken(
                            null,
                            false,
                            "User does not have permission."
                    ),
                    HttpStatus.FORBIDDEN
            );
        }

        var signupResponse = userService.signup(obj);

        if(signupResponse.user() == null) {
            return new ResponseEntity<>(
                    new AuthToken(
                            null,
                            false,
                            signupResponse.message()
                    ),
                    HttpStatus.BAD_REQUEST);
        }

        var token = jwtService.getToken(
                new JWTPayload(
                    obj.username(),
                    obj.role()
        ));

        return new ResponseEntity<>(
                new AuthToken(
                        token,
                        true,
                        signupResponse.message()
                ),
                HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthToken> login(
            @RequestBody LoginData obj) {

        var signinResponse = userService.signin(
                obj.username(),
                obj.password()
        );

        if(signinResponse.user() == null) {
            return new ResponseEntity<>(
                    new AuthToken(
                            null,
                            false,
                            signinResponse.message()
                    ),
                    HttpStatus.OK);
        }
        var token = jwtService.getToken(
                new JWTPayload(
                        signinResponse.user().getUsername(),
                        signinResponse.user().getRole()
                )
        );

        return new ResponseEntity<>(
                new AuthToken(
                        token,
                        true,
                        signinResponse.message()
                ),
                HttpStatus.OK);
    }
}
