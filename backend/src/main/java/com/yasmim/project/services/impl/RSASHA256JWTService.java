package com.yasmim.project.services.impl;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.Objects;

import com.yasmim.project.services.exceptions.ForbiddenException;
import com.yasmim.project.services.exceptions.UnauthorizedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import com.yasmim.project.dto.JWTPayload;
import com.yasmim.project.services.JWTService;
import com.yasmim.project.services.KeyService;
import org.springframework.beans.factory.annotation.Value;

public class RSASHA256JWTService implements JWTService {

    @Autowired
    private KeyService keyService;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiryInSeconds}")
    private int expiryInSeconds;

    @Override
    public String getToken(JWTPayload userData) {
        
        try {
            Algorithm algorithm = Algorithm.RSA256(
                (RSAPublicKey) keyService.getKeys().getPublic(), 
                (RSAPrivateKey) keyService.getKeys().getPrivate());

            return JWT.create()
                    .withIssuer(issuer)
                    .withClaim("username", userData.username())
                    .withClaim("role", userData.role())
                    .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * expiryInSeconds)))
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
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new ForbiddenException("Permission denied");
        }

        var role = authentication.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority().replace("ROLE_", ""))
                .findFirst()
                .orElseThrow(() -> new ForbiddenException("Permission denied"));

        if(!Objects.equals(Integer.parseInt(role), permissionLevel)) {
            throw new ForbiddenException("Permission denied");
        }
    }
}
