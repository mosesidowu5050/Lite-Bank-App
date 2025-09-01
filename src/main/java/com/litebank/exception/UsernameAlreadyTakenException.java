package com.litebank.exception;

public class UsernameAlreadyTakenException extends AccountNotFoundException {
    public UsernameAlreadyTakenException(String usernameAlreadyTaken) {
        super(usernameAlreadyTaken);
    }
}
