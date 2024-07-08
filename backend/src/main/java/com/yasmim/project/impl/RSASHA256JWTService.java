package com.yasmim.project.impl;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Objects;

import com.yasmim.project.exception.ForbiddenException;
import com.yasmim.project.exception.UnauthorizedException;
import com.yasmim.project.session.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import com.yasmim.project.dto.JWTPayload;
import com.yasmim.project.service.JWTService;
import com.yasmim.project.service.KeyService;

public class RSASHA256JWTService implements JWTService {

    @Autowired
    private KeyService keyService;

    @Autowired
    private UserSession userSession;

    @Override
    public String getToken(JWTPayload userData) {
        
        try {
            Algorithm algorithm = Algorithm.RSA256(
                (RSAPublicKey) keyService.getKeys().getPublic(), 
                (RSAPrivateKey) keyService.getKeys().getPrivate());

            return JWT.create()
                    .withIssuer("yasmim")
                    .withClaim("username", userData.username())
                    .withClaim("role", userData.role())
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new UnauthorizedException("Failed to create JWT");
        }
    }

    @Override
    public DecodedJWT verifyToken(String token) {
        
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.RSA256(
                (RSAPublicKey) keyService.getKeys().getPublic(), 
                (RSAPrivateKey) keyService.getKeys().getPrivate());

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("yasmim")
                    .build();

            decodedJWT = verifier.verify(token);

            return decodedJWT;

        } catch (JWTVerificationException exception) {
            throw new UnauthorizedException("Failed to verify token");
        }
    }

    @Override
    public void verifyPermission(Integer permissionLevel) {
        if(!Objects.equals(userSession.getRole(), permissionLevel)) {
            throw new ForbiddenException("Permission denied");
        }
    }
}
