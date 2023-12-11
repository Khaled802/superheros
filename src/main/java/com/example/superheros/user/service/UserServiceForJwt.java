package com.example.superheros.user.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.superheros.auth.service.UserInfoService;
import com.example.superheros.exception.NotFoundException;
import com.example.superheros.user.entity.User;
import com.example.superheros.user.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceForJwt implements UserService {

    private UserRepository userRepository;
	private PasswordEncoder encoder; 

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUUID(UUID uuid) {
        return userRepository.findById(uuid).orElseThrow(() -> new NotFoundException("not found user"));
    }

    @Override
    public void createUser(User user) {
        log.info(user.getPassword());
        user.setPassword(encoder.encode(user.getPassword())); 
		userRepository.save(user);
    }

    @Override
    public User updateUser(UUID uuid, User user) {
        user.setUuid(uuid);
        return userRepository.save(user);
    }

    @Override
    public void delete(UUID uuid) {
        if (!userRepository.existsById(uuid))
            throw new NotFoundException("not found user");
        userRepository.existsById(uuid);
    }

}
