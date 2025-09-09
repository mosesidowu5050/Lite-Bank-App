package com.litebank.security.service;

import com.auth0.jwt.JWT;
import com.litebank.dtos.response.TokenResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LiteBankAuthService implements AuthService {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    public TokenResponse generateTokens(String refreshToken) {
        String username = JWT.decode(refreshToken).getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new TokenResponse(jwtService.
                generateAccessToken(username, userDetails.getAuthorities()),
                jwtService.generateRefreshToken(username));
    }
}