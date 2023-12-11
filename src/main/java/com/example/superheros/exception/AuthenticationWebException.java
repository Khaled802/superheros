package com.example.superheros.exception;

import org.springframework.http.HttpStatus;

public class AuthenticationWebException extends CustomeWebException {
    public AuthenticationWebException() {
        super("Not correct authentication", HttpStatus.UNAUTHORIZED);
    }

    public AuthenticationWebException(String msg) {
        super(msg, HttpStatus.UNAUTHORIZED);
    }
    
}
