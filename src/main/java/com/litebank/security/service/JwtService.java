package com.litebank.security.service;

import com.auth0.jwt.interfaces.Claim;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface JwtService {

    String generateAccessToken(String username, Collection<? extends GrantedAuthority> authorities);

    String generateRefreshToken(String username);

    boolean isJwtTokenValid(String token);

    Claim extractClaim(String token, String claimName);
}
