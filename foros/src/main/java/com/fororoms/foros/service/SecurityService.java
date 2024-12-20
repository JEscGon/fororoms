package com.fororoms.foros.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fororoms.foros.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Autowired
    private JwtUtils jwtUtils;

    public boolean isUserIdMatching(Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = (String) authentication.getCredentials();
        DecodedJWT decodedJWT = jwtUtils.validateToken(token.replace("Bearer ", ""));
        Long userIdFromToken = Long.valueOf(jwtUtils.extractUserId(decodedJWT));
        return userIdFromToken.equals(userId);
    }
}