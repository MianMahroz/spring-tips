package com.mahroz.identity.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    public AuthService(PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

}
