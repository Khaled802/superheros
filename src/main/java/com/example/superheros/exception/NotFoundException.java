package com.example.superheros.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

public class NotFoundException extends CustomeWebException {
    public NotFoundException(String msg) {
        super(msg, HttpStatus.NOT_FOUND);
    }

    public NotFoundException(String msg, List<String> messageList) {
        super(msg, HttpStatus.NOT_FOUND, messageList);
    }
    
}
