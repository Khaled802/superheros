package com.example.superheros.user.service;

import java.util.List;
import java.util.UUID;

import com.example.superheros.user.entity.User;

public interface UserService {
    List<User> getUsers();
    User getUserByUUID(UUID uuid);
    void createUser(User user);
    User updateUser(UUID uuid, User user);
    void delete(UUID uuid); 
}
