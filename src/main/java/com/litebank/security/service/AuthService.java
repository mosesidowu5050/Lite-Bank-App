package com.litebank.security.service;

import com.litebank.dtos.response.TokenResponse;

public interface AuthService {
    TokenResponse generateTokens(String refreshToken);
}