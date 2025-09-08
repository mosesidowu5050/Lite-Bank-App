package com.litebank.dtos.response;

import com.litebank.model.Authority;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AccountResponse {

    private String username;
    private String password;
    private String accountNumber;
    private Set<Authority> authorities;

}
