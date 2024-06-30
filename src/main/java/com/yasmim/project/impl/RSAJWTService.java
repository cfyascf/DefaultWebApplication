package com.yasmim.project.impl;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import com.yasmim.project.service.JWTService;
import com.yasmim.project.service.KeyService;
import org.springframework.beans.factory.annotation.Autowired;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.yasmim.project.dto.JWTPayload;

public class RSAJWTService implements JWTService {

    @Autowired
    private KeyService keyService;

    @Override
    public String getToken(JWTPayload userData) {
        
        try {
            Algorithm algorithm = Algorithm.RSA256(
                (RSAPublicKey) keyService.getKeys().getPublic(), 
                (RSAPrivateKey) keyService.getKeys().getPrivate());

            return JWT.create()
                    .withIssuer("auth0")
                    .withClaim("username", userData.username())
                    .withClaim("role", userData.role())
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            return "";
        }
    }

    @Override
    public DecodedJWT verifyToken(String token, JWTPayload userData) {
        
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.RSA256(
                (RSAPublicKey) keyService.getKeys().getPublic(), 
                (RSAPrivateKey) keyService.getKeys().getPrivate());

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .withClaim("username", userData.username())
                    .withClaim("role", userData.role())
                    .build();

            decodedJWT = verifier.verify(token);

            return decodedJWT;

        } catch (JWTVerificationException exception) {
            return null;
        }
    }
}
