package com.example.superheros.auth.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.superheros.user.entity.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserInfoDetails implements UserDetails {
    private String username; 
    private String password; 
    private List<GrantedAuthority> authorities; 
  
    public UserInfoDetails(User userInfo) { 
        username = userInfo.getEmail(); 
        password = userInfo.getPassword(); 
        authorities =  userInfo.getRoles() == null? List.of() : Arrays.stream(userInfo.getRoles().split(",")) 
                .map(SimpleGrantedAuthority::new) 
                .collect(Collectors.toList()); 
    } 
  
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { 
        return authorities; 
    } 
  
    @Override
    public String getPassword() { 
        return password; 
    } 
  
    @Override
    public String getUsername() { 
        log.info(username);
        return username; 
    } 
  
    @Override
    public boolean isAccountNonExpired() { 
        return true; 
    } 
  
    @Override
    public boolean isAccountNonLocked() { 
        return true; 
    } 
  
    @Override
    public boolean isCredentialsNonExpired() { 
        return true; 
    } 
  
    @Override
    public boolean isEnabled() { 
        return true; 
    }
}
