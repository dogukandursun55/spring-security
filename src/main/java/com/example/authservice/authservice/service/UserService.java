package com.example.authservice.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.authservice.authservice.advice.exception.FieldAlreadyExistsException;
import com.example.authservice.authservice.advice.exception.UserNotFoundException;
import com.example.authservice.authservice.domain.User;
import com.example.authservice.authservice.mapper.UserMapper;
import com.example.authservice.authservice.model.dto.UserDto;
import com.example.authservice.authservice.model.request.LoginRequest;
import com.example.authservice.authservice.model.request.RegisterUserRequest;
import com.example.authservice.authservice.model.response.ApiResponse;
import com.example.authservice.authservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    private  UserMapper userMapper;
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username); 
    }
    public boolean existsByEmailAddress(String email) {
        return userRepository.existsByEmailAddress(email); 
    }
    public boolean existsByPhoneNumber(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber); 
    }

    public UserDto save(RegisterUserRequest request) {
        return userMapper.toDto(userRepository.save(userMapper.toEntity(request)));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException());
    }

    


}
