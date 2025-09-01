package com.litebank.controller;

import com.litebank.dtos.request.DepositRequest;
import com.litebank.dtos.request.CreateAccountRequest;
import com.litebank.dtos.response.DepositResponse;
import com.litebank.dtos.response.ErrorResponse;
import com.litebank.dtos.response.CreateAccountResponse;
import com.litebank.exception.AccountNotFoundException;
import com.litebank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(DepositRequest depositRequest) {
        try {
            DepositResponse response = accountService.deposit(depositRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/create-account")
    public ResponseEntity<?> createAccount(@RequestBody CreateAccountRequest registerAccount) {
        try {
            CreateAccountResponse response = accountService.createAccount(registerAccount);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }

}
