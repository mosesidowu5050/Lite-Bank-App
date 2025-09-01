package com.litebank.dtos.response;

import com.litebank.model.AccountStatus;
import com.litebank.model.AccountType;
import lombok.Data;

@Data
public class CreateAccountResponse {

    private String accountNumber;
    private String message;
    private String accountHolderName;
    private AccountType accountType;

}
