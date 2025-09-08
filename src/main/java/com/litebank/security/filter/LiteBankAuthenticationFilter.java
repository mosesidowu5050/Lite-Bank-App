package com.litebank.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.litebank.security.dto.AuthRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class LiteBankAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;
    private final ObjectMapper mapper;

    @Value("${jwt.signing.key}")
    private String signingKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try{
                if (!request.getServletPath().equals("/login")) {
                    filterChain.doFilter(request, response);
                    return;
                }

                InputStream requestBody = request.getInputStream(); //{"username":"", "password":""} what we have here is json
                AuthRequest authRequest = mapper.readValue(requestBody, AuthRequest.class);
                String username = authRequest.getUsername();
                String password = authRequest.getPassword();
                Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
                Authentication authResult = authenticationManager.authenticate(authentication);

                if (authResult.isAuthenticated()) {
                    String jwt = JWT.create()
                            .withIssuer("https://litebank.com")
                            .withIssuedAt(Instant.now())
                            .withExpiresAt(Instant.now().plusSeconds(60 * 60 * 24))
                            .withSubject(username)
                            .withClaim("roles", authResult.getAuthorities()
                                    .stream()
                                    .map(a-> a.getAuthority()).toList())
                            .sign(Algorithm.HMAC256(signingKey));

                    Map<String, String> loginResponse = new HashMap<>();
                    loginResponse.put("access_token", jwt);
                    loginResponse.put("username", username);
                    response.setContentType("application/json");
                    response.getOutputStream().write(mapper.writeValueAsBytes(loginResponse));
                    response.flushBuffer();
                }

        } catch(Exception exception){
                Map<String, String> loginResponse = new HashMap<>();
                loginResponse.put("error", exception.getMessage());
                response.setContentType("application/json");
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.getOutputStream().write(mapper.writeValueAsBytes(loginResponse));
                response.flushBuffer();
        }
       filterChain.doFilter(request, response);
    }


}
