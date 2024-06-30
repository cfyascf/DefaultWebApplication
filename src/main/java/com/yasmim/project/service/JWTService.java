package com.yasmim.project.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.yasmim.project.dto.JWTPayload;

public interface JWTService {
    public String getToken(JWTPayload userData);
    public DecodedJWT verifyToken(String token, JWTPayload userData);
}
