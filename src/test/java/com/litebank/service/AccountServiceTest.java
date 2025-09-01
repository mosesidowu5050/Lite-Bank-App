package com.litebank.service;

import com.litebank.dtos.request.DepositRequest;
import com.litebank.model.AccountStatus;
import com.litebank.model.PaymentMethod;
import com.litebank.dtos.request.CreateAccountRequest;
import com.litebank.dtos.response.*;
import com.litebank.model.TransactionStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.io.IOException;
import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = {"/db/data.sql"})
public class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Test
    void testCanDeposit() throws IOException {
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAccountNumber("123456789");
        depositRequest.setAmount(new BigDecimal("1000.00"));
        depositRequest.setPaymentMethod(PaymentMethod.CARD);

        DepositResponse depositResponse = accountService.deposit(depositRequest);
        assertNotNull(depositResponse);
        assertEquals(TransactionStatus.SUCCESS, depositResponse.getTransactionStatus());
    }

    @Test
    void testCanViewAccount() {
        ViewAccountResponse response = accountService.viewDetailsFor("123456789");

        assertThat(response).isNotNull();
        assertThat(response.getBalance()).isEqualTo(new BigDecimal("370000.00").toString());
    }

    @Test
    void testCanCreateAccount(){
        CreateAccountRequest registerRequest = new CreateAccountRequest();
        registerRequest.setName("Suliyat Olanrewaju");
        registerRequest.setUsername("sulisu");
        registerRequest.setPassword("password123");

        CreateAccountResponse response = accountService.createAccount(registerRequest);
        assertThat(response).isNotNull();
        assertNotNull(response.getAccountNumber());
    }
}
