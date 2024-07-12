package com.yasmim.project.services;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.yasmim.project.dto.JWTPayload;
import com.yasmim.project.entities.UserData;
import com.yasmim.project.entities.VerificationTokenData;
import com.yasmim.project.repositories.VerificationTokenRepository;
import org.springframework.stereotype.Service;

@Service
public interface JWTService {
    public String getToken(JWTPayload userData);
    public VerificationTokenData createVerificationToken(UserData user);
    public DecodedJWT verifyToken(String token);
    public void verifyPermission(Integer permissionLevel);
}
