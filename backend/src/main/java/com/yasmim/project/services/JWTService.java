package com.yasmim.project.services;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.yasmim.project.dto.JWTPayload;
import org.springframework.stereotype.Service;

@Service
public interface JWTService {
    public String getToken(JWTPayload userData);
    public DecodedJWT verifyToken(String token);
    public void verifyPermission(Integer permissionLevel);
}
