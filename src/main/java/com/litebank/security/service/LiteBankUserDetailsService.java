package com.litebank.security.service;

import com.litebank.dtos.response.AccountResponse;
import com.litebank.security.model.User;
import com.litebank.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LiteBankUserDetailsService implements UserDetailsService {

    private final AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountResponse accountResponse = accountService.getByUsername(username);
        return new User(accountResponse);
    }
}
