package com.example.superheros.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public abstract class CustomeWebException extends RuntimeException {
    @Getter
    private List<String> messageList = new ArrayList<>();

    @Getter
    private HttpStatus status;

    CustomeWebException(String msg, HttpStatus httpStatus) {
        super(msg);
        status = httpStatus;
    }

    CustomeWebException(String msg, HttpStatus httpStatus, List<String> messagesList) {
        super(msg);
        status = httpStatus;
        this.messageList = messagesList.stream().toList();
    }

  


    
}
