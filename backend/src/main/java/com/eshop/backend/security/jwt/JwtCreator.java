package com.eshop.backend.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

import static com.eshop.backend.constants.SecurityConstants.EXPIRATION_TIME;
import static com.eshop.backend.constants.SecurityConstants.SECRET;

public class JwtCreator {

    static public String createJwt(String login) {
        return JWT.create()
                .withSubject(login)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
    }

}
