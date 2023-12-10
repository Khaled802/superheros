package com.example.superheros.dto;

import java.util.List;

import lombok.Value;

@Value
public class MessageValidationDto {
    private String message;
    private List<ValidationItemDto> messageDetails;
}
