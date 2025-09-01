package com.litebank.dtos.response;

import com.litebank.model.TransactionType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionResponse {

    private String amount;
    private TransactionType transactionType;

}
