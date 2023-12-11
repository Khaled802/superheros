package com.example.superheros.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.superheros.auth.dto.LoginDto;
import com.example.superheros.auth.dto.RegisterDto;
import com.example.superheros.auth.service.AuthService;
import com.example.superheros.auth.util.JwtAuthFilter;
import com.example.superheros.dto.MessageDto;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;
    
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    private MessageDto register(@RequestBody RegisterDto registerDto) {
        authService.register(registerDto);
        return new MessageDto("created successfully", null);
    }

    @PostMapping("/login")
    private MessageDto register(@RequestBody LoginDto loginDto) {
        String jwtToken = authService.login(loginDto);
        return new MessageDto(JwtAuthFilter.TOKEN_PREFIX + jwtToken, null);
    }   
}
