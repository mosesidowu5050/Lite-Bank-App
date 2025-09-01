package com.litebank.exception;

public class TransactionIdNotFoundException extends AccountNotFoundException {
    public TransactionIdNotFoundException(String message) {
        super(message);
    }
}
