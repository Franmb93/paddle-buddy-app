package com.fmb.userservice.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fmb.userservice.services.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {
    // TODO: Change secret key to properties. Avoid upload.
    private final String SECRET_KEY = "asdf123asdf";
    private final long EXPIRATION_TIME = 900_000;

    @Override
    public String generateToken(String email) {
        return JWT.create()
                .withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET_KEY));
    }

    @Override
    public String validateToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC512(SECRET_KEY.getBytes()))
                    .build();

            verifier.verify(token);

            DecodedJWT jwt = verifier.verify(token);

            return jwt.getSubject();
        } catch (TokenExpiredException e) {
            log.debug("Token expired: " + e.getLocalizedMessage());
            throw e;
        } catch (JWTVerificationException e) {
            log.debug("Invalid token: " + e.getLocalizedMessage());
            throw e;
        }
    }
}
