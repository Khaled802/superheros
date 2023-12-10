package com.example.superheros.dto;

import java.util.List;

import lombok.Value;

@Value
public class MessageDto {
    private String message;
    private List<String> messageList;
}
