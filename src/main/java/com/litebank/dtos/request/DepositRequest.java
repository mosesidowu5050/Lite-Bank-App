package com.litebank.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.litebank.model.PaymentMethod;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Setter
@Getter
public class DepositRequest {

    private String accountNumber;

    private BigDecimal amount;

    @JsonProperty("payment_method")
    private PaymentMethod paymentMethod;

}
