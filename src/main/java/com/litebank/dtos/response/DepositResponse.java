package com.litebank.dtos.response;

import com.litebank.model.TransactionStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter

public class DepositResponse {

    private TransactionStatus transactionStatus;
    private String transactionId;
    private BigDecimal amount;

}
