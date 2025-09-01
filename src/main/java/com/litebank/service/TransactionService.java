package com.litebank.service;

import com.litebank.dtos.request.CreateTransactionRequest;
import com.litebank.dtos.response.CreateTransactionResponse;
import com.litebank.dtos.response.TransactionResponse;

import java.util.List;


public interface TransactionService {

    CreateTransactionResponse createTransaction(CreateTransactionRequest transactionRequest);

    TransactionResponse getTransactionBy(String id);

    List<TransactionResponse> getTransactionsFor(String accountNumber);

}
