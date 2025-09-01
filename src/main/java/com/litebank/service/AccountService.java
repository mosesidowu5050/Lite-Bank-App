package com.litebank.service;

import com.litebank.dtos.request.DepositRequest;
import com.litebank.dtos.request.CreateAccountRequest;
import com.litebank.dtos.response.DepositResponse;
import com.litebank.dtos.response.CreateAccountResponse;
import com.litebank.dtos.response.ViewAccountResponse;

import java.io.IOException;


public interface AccountService  {

    DepositResponse deposit(DepositRequest depositRequest) throws IOException;

    ViewAccountResponse viewDetailsFor(String number);

    CreateAccountResponse createAccount(CreateAccountRequest registerAccount);
}
