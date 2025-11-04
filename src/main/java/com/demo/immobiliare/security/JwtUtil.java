package com.demo.immobiliare.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.demo.immobiliare.config.JwtProperties;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

	private final Algorithm algorithm;
    private final long expiration;

    public JwtUtil(JwtProperties jwtProperties) {
        this.algorithm = Algorithm.HMAC256(jwtProperties.getSecret());
        this.expiration = jwtProperties.getExpiration();
    }

    public String generateToken(String username, Long userId) {
        return JWT.create()
            .withSubject(username)
            .withClaim("userId", userId)
            .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
            .sign(algorithm);
    }

    public String validateTokenAndRetrieveSubject(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getSubject();
    }
    
    public long getExpirationTime() {
    	return this.expiration;
    }
}
