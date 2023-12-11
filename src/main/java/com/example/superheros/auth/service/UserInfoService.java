package com.example.superheros.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.superheros.auth.util.UserInfoDetails;
import com.example.superheros.exception.NotFoundException;
import com.example.superheros.user.entity.User;
import com.example.superheros.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserInfoService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> userDetail = repository.findByEmail(username);

		log.info(userDetail.orElse(null).toString());

		return userDetail.map((user)-> new UserInfoDetails(user))
				.orElseThrow(() -> new NotFoundException("User not found " + username));
	}
}
