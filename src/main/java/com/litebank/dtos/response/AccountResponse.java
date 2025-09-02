package com.litebank.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountResponse {

    private String username;
    private String password;
    private String accountNumber;
}
