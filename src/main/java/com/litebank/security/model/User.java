package com.litebank.security.model;

import com.litebank.dtos.response.AccountResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class User implements UserDetails {

//    public static void main(String[] args) {
//        System.out.println(Base64.getEncoder().encodeToString("This is a jwt token".getBytes()));
//    }

    private AccountResponse accountResponse;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
            List<SimpleGrantedAuthority> authorities = accountResponse.getAuthorities()  //[ACCOUNT, ADMIN]
                    .stream()
                    .map((authority )->
                            new SimpleGrantedAuthority(authority.name()))//[ACCOUNT, ADMIN] -> [GrantedAuthority(ACCOUNT), grantedAuthority(ADMIN)]
                    .toList();
            return authorities;
        }


    @Override
    public String getPassword() {
        return accountResponse.getPassword();
    }

    @Override
    public String getUsername() {
        return accountResponse.getUsername();
    }

}
