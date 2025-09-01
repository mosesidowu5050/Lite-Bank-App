package com.litebank.dtos.request;

import com.litebank.model.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Setter
@Getter
public class DepositRequest {

    private String accountNumber;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;

}
