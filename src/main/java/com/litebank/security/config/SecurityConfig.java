package com.litebank.security.config;

import com.litebank.security.filter.LiteBankAuthenticationFilter;
import com.litebank.security.filter.LiteBankAuthorizationFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.sql.DataSource;

import static com.litebank.model.Authority.ACCOUNT;
import static com.litebank.model.Authority.ADMIN;
import static com.litebank.util.ProjectUtil.PUBLIC_ENDPOINTS;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final LiteBankAuthenticationFilter authenticationFilter;
    private final LiteBankAuthorizationFilter authorizationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(c -> c.disable())
                .cors(Customizer.withDefaults())
                .addFilterAt(authenticationFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(authorizationFilter, LiteBankAuthenticationFilter.class)
                .authorizeHttpRequests(c ->
                        c.requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS).permitAll())
                .authorizeHttpRequests(c ->
                        c.requestMatchers("/api/v1/transactions/**", "/api/v1/account/**")
                                .hasAnyAuthority(ACCOUNT.name(), ADMIN.name()))
                .build();
    }

}
