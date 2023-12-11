package com.example.superheros.auth.dto;

import com.example.superheros.user.entity.User;

import jakarta.validation.constraints.Min;
import lombok.Value;

@Value
public class RegisterDto {
    private String email;
    @Min(value = 7, message = "make password with more values")
    private String password;
    private String phoneNumber;

    public User convertToUserEntity() {
        return new User(null, email, phoneNumber, password, null, null, null);
    }
}
