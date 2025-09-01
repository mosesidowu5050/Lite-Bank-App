package com.litebank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.litebank.dtos.request.CreateTransactionRequest;
import com.litebank.model.TransactionType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"/db/data.sql"})
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getTransactionByAccountNumber() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String accountNumber = "123456789";
        String json = mapper.writeValueAsString(accountNumber);

        String transactionEndpoint = "/api/v1/transactions";
        mockMvc.perform(MockMvcRequestBuilders.get(transactionEndpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void canCreateTransaction() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CreateTransactionRequest createTransactionRequest = new CreateTransactionRequest();
        createTransactionRequest.setAccountNumber("123456789");
        createTransactionRequest.setAmount(new BigDecimal("100000"));
        createTransactionRequest.setTransactionType(TransactionType.DEPOSIT);
        String transaction = mapper.writeValueAsString(createTransactionRequest);

        String transactionEndpoint = "/api/v1/create-transactions";
        mockMvc.perform(MockMvcRequestBuilders.post(transactionEndpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(transaction))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }
}
