package com.example.superheros.auth.dto;

import lombok.Value;

@Value
public class LoginDto {
    private String email;
    private String password;
}
