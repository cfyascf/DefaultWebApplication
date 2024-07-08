package com.yasmim.project.filter;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yasmim.project.impl.RSASHA256JWTService;
import com.yasmim.project.service.JWTService;
import com.yasmim.project.session.UserSession;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JWTFilter implements Filter {

    @Autowired
    JWTService jwtService;

    @Autowired
    UserSession userSession;

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String token = request.getHeader("Authorization");

        if(token != null) {
            var auth = jwtService.verifyToken(token);

            var role = getClaimStringfied(auth, "role");
            var username = getClaimStringfied(auth, "username");

            userSession.setRole(Integer.parseInt(role));
            userSession.setUsername(username);
        }

        chain.doFilter(request, response);
    }

    public String getClaimStringfied(DecodedJWT jwt, String claimkey) {
        var claim = jwt.getClaim(claimkey);
        Object roleValue = claim.as(Object.class);

        return String.valueOf(roleValue);
    }
}
