package com.example.superheros.dto;

import lombok.Value;

@Value
public class ValidationItemDto {
    private String field;
    private String value;
    private String message;
}
