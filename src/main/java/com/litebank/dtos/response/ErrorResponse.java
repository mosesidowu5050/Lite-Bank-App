package com.litebank.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse <T> {

    private T message;

    public ErrorResponse(T message) {
        this.message = message;
    }
}
