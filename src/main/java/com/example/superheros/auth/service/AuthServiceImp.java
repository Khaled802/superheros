package com.example.superheros.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.superheros.auth.dto.LoginDto;
import com.example.superheros.auth.dto.RegisterDto;
import com.example.superheros.user.service.UserService;
import com.example.superheros.exception.AuthenticationWebException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImp implements AuthService {
    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    @Override
    public void register(RegisterDto registerDto) {
        log.info(registerDto.toString());
        userService.createUser(registerDto.convertToUserEntity());
    }

    @Override
    public String login(LoginDto loginDto) throws AuthenticationWebException {
        log.info(loginDto.toString());
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        log.info(authentication.toString());
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(loginDto.getEmail());
        }
        throw new AuthenticationWebException();

    }

}
