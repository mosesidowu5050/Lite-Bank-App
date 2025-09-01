package com.litebank.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateTransactionResponse {

    private String id;
    private String transactionType;
    private String amount;

}
