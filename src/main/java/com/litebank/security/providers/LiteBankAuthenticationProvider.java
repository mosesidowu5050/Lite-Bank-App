package com.litebank.security.providers;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@AllArgsConstructor
public class LiteBankAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String savePassword =userDetails.getPassword();

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        boolean isPasswordValidMatch = passwordEncoder.matches(password, savePassword);

        if(isPasswordValidMatch){
            return new UsernamePasswordAuthenticationToken(username, null, null);
        }
        throw new BadCredentialsException("Invalid auth credentials supplied, try again later");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
