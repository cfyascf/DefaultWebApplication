package com.yasmim.project.security.filters;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.yasmim.project.services.JWTService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    JWTService jwtService;

    @Override
    public void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        String token = request.getHeader("Authorization");

        if(token != null) {
            var decodedJWT = jwtService.verifyToken(token);
            var role = getClaimStringfied(decodedJWT, "role");
            var username = getClaimStringfied(decodedJWT, "username");

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    List.of(new SimpleGrantedAuthority("ROLE_" + role))
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        chain.doFilter(request, response);
    }

    public String getClaimStringfied(DecodedJWT jwt, String claimkey) {
        var claim = jwt.getClaim(claimkey);
        Object roleValue = claim.as(Object.class);

        return String.valueOf(roleValue);
    }
}
