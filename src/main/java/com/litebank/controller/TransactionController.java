package com.litebank.controller;

import com.litebank.dtos.request.CreateTransactionRequest;
import com.litebank.dtos.response.CreateTransactionResponse;
import com.litebank.dtos.response.ErrorResponse;
import com.litebank.dtos.response.TransactionResponse;
import com.litebank.exception.AccountNotFoundException;
import com.litebank.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/transactions/{accountNumber}")
    public ResponseEntity<?> getTransactionByAccountNumber(@PathVariable String accountNumber) {
        try {
            List<TransactionResponse> transaction = transactionService.getTransactionsFor(accountNumber);
            return ResponseEntity.status(HttpStatus.OK).body(transaction);
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }

    @PostMapping("/create-transactions")
    public ResponseEntity<?> createTransaction(@RequestBody CreateTransactionRequest transactionRequest) {
        try {
            CreateTransactionResponse transactionResponse = transactionService.createTransaction(transactionRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(transactionResponse);
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }
}
