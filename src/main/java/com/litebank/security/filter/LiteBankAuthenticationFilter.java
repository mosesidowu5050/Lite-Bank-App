package com.litebank.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.litebank.security.dto.AuthRequest;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.InputStream;

@Component
@AllArgsConstructor
public class LiteBankAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        InputStream requestBody = request.getInputStream();
        byte[] body = requestBody.readAllBytes();

        AuthRequest authRequest = mapper.readValue(body, AuthRequest.class);
        String username = authRequest.getUsername();
        String password = authRequest.getPassword();

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authResult = authenticationManager.authenticate(authentication);
    }
}
