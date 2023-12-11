package com.example.superheros.exception;

import org.springframework.http.HttpStatus;

public class TokenInvalidException extends CustomeWebException {
    public TokenInvalidException() {
        super("the token is invalid", HttpStatus.UNAUTHORIZED);
    }
    public TokenInvalidException(String msg) {
        super(msg, HttpStatus.UNAUTHORIZED);
    }
    
}
