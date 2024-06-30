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
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<AuthToken> signup(@RequestBody RegisterData obj) {

        var signupReponse = userService.signup(obj);

        if(signupReponse.user() == null) {
            return new ResponseEntity<>(
                    new AuthToken(
                            null,
                            false,
                            signupReponse.message()
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
                        signupReponse.message()
                ),
                HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthToken> login(@RequestBody LoginData obj) {

        var signResponse = userService.signin(
                obj.username(),
                obj.password()
        );

        if(signResponse.user() == null) {
            return new ResponseEntity<>(
                    new AuthToken(
                            null,
                            false,
                            signResponse.message()
                    ),
                    HttpStatus.OK);
        }
        var token = jwtService.getToken(
                new JWTPayload(
                        signResponse.user().getUsername(),
                        signResponse.user().getRole()
                )
        );

        return new ResponseEntity<>(
                new AuthToken(
                        token,
                        true,
                        signResponse.message()
                ),
                HttpStatus.OK);
    }
}
