package com.litebank.service;

import com.litebank.dtos.request.CreateTransactionRequest;
import com.litebank.model.TransactionType;
import com.litebank.dtos.response.CreateTransactionResponse;
import com.litebank.dtos.response.TransactionResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Slf4j
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    @Test
    void testTransactionService(){
        CreateTransactionRequest transactionRequest = new CreateTransactionRequest();
        transactionRequest.setTransactionType(TransactionType.CREDIT);
        transactionRequest.setAmount(new BigDecimal("20000.00"));
        transactionRequest.setAccountNumber("1234567890");

        CreateTransactionResponse transactionResponse = transactionService.createTransaction(transactionRequest);
        assertNotNull(transactionResponse);
        TransactionResponse transaction = transactionService.getTransactionBy(transactionResponse.getId());

        log.info("transaction response---> {}", transaction);
        assertThat(transaction).isNotNull();
        assertThat(transaction.getAmount()).isEqualTo(transactionRequest.getAmount().toString());
    }



}
