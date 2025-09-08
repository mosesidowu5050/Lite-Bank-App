package com.litebank.security.config;

import com.litebank.security.filter.LiteBankAuthenticationFilter;
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

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final LiteBankAuthenticationFilter authenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(c -> c.disable())
                .cors(Customizer.withDefaults())
                .addFilterAt(authenticationFilter, BasicAuthenticationFilter.class)
                .authorizeHttpRequests(c->c.requestMatchers(HttpMethod.POST,
                        "/api/v1/account/create-account", "/login").permitAll())
                .authorizeHttpRequests(c->c.anyRequest().authenticated())
                .build();
    }


//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider) throws Exception{
//        return (authentication) -> authenticationProvider.authenticate(authentication);
//    }

//    @Bean
//    public UserDetailsService userDetailsService(@Autowired DataSource dataSource) throws Exception {
//        return new JdbcUserDetailsManager();
//    }


}
