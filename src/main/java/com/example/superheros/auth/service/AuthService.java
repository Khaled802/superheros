package com.example.superheros.auth.service;

import com.example.superheros.auth.dto.LoginDto;
import com.example.superheros.auth.dto.RegisterDto;
import com.example.superheros.exception.AuthenticationWebException;

public interface AuthService {
    void register(RegisterDto registerDto);
    String login(LoginDto loginDto) throws AuthenticationWebException;
}
