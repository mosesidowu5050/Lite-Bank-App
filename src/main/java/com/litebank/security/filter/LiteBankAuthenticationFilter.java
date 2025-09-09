package com.litebank.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.litebank.security.dto.AuthRequest;
import com.litebank.security.service.JwtService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class LiteBankAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;
    private final ObjectMapper mapper;
    private final JwtService jwtService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if (!request.getServletPath().equals("/login")) {
            filterChain.doFilter(request, response);
            return;
        }
        InputStream requestBody = request.getInputStream(); //{"username":"", "password":""}
        AuthRequest authRequest = mapper.readValue(requestBody, AuthRequest.class);
        String username = authRequest.getUsername();
        String password = authRequest.getPassword();
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(username, password);
        Authentication authResult = authenticationManager.authenticate(authentication);
        if (authResult.isAuthenticated()) sendSuccessfulAuthResponse(response, authResult);
        else throw new BadCredentialsException("authentication failed for user: " + username);
        filterChain.doFilter(request, response);
    }

    private void sendSuccessfulAuthResponse(HttpServletResponse response, Authentication authResult) throws IOException {
        Map<String, String> loginResponse = new HashMap<>();
        loginResponse.put("access_token", jwtService.generateAccessToken(authResult.getPrincipal().toString(), authResult.getAuthorities()));
        loginResponse.put("refresh_token", jwtService.generateRefreshToken(authResult.getPrincipal().toString()));
        response.setContentType("application/json");
        response.getOutputStream().write(mapper.writeValueAsBytes(loginResponse));
        response.flushBuffer();
    }
}
