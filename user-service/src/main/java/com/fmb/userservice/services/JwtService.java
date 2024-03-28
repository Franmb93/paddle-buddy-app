package com.fmb.userservice.services;

import com.auth0.jwt.exceptions.JWTVerificationException;

public interface JwtService {

    public String generateToken(String email);
    public String validateToken(String token);
}
