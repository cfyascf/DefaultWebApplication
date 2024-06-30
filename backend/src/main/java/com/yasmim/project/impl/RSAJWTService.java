package com.yasmim.project.impl;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
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
                    .withIssuer("yasmim")
                    .withClaim("username", userData.username())
                    .withClaim("role", userData.role())
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            return "";
        }
    }

    @Override
    public Boolean verifyPermission(String token, Integer permissionLevel) {
        if(token == null) {
            return false;
        }

        var auth = verifyToken(token);
        if(auth == null) {
            return false;
        }

        if(permissionLevel == null) {
            return true;
        }

        var claim = auth.getClaims().get("role");
        Object roleValue = claim.as(Object.class); // Adjust the class type as per your claim's expected type
        String roleAsString = String.valueOf(roleValue);

        return Integer.parseInt(roleAsString) == permissionLevel;
    }


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
            return null;
        }
    }
}
