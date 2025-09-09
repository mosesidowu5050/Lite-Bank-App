package com.litebank.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;

@Service
public class LiteBankJwtService implements JwtService {

    @Value("${jwt.signing.key}")
    private String signingKey;

    @Override
    public String generateAccessToken(String username, Collection<? extends GrantedAuthority> authorities) {
        return JWT.create()
                .withIssuer("https://www.litebank.com")
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(60 * 60 * 24))
                .withSubject(username)
                .withClaim("roles", authorities
                        .stream().map(GrantedAuthority::getAuthority).toList())
                .sign(Algorithm.HMAC256(signingKey));
    }

    @Override
    public String generateRefreshToken(String username) {
        return JWT.create()
                .withIssuer("https://www.litebank.com")
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(60 * 60 * 120))
                .withSubject(username)
                .sign(Algorithm.HMAC256(signingKey));
    }

    @Override
    public boolean isJwtTokenValid(String token) {
        return false;
    }

    @Override
    public Claim extractClaim(String token, String claimName) {
        return JWT.decode(token).getClaim(claimName);
    }
}