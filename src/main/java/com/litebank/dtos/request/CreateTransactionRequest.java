package com.litebank.dtos.request;

import com.litebank.model.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CreateTransactionRequest {

    private TransactionType transactionType;
    private BigDecimal amount;
    private String accountNumber;
}
