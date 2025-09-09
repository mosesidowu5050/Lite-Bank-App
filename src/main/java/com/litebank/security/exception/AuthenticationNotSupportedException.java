package com.litebank.security.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthenticationNotSupportedException extends AuthenticationException {
    public AuthenticationNotSupportedException(String message) {
        super(message);
    }
}
