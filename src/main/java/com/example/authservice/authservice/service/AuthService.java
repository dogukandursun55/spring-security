package com.example.authservice.authservice.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.authservice.authservice.advice.exception.FieldAlreadyExistsException;
import com.example.authservice.authservice.domain.User;
import com.example.authservice.authservice.model.dto.UserDto;
import com.example.authservice.authservice.model.request.LoginRequest;
import com.example.authservice.authservice.model.request.RegisterUserRequest;
import com.example.authservice.authservice.model.response.LoginResponse;
import com.example.authservice.authservice.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public UserDto registerUser(RegisterUserRequest request) {
        if (userService.existsByUsername(request.getUsername())) {
            throw new FieldAlreadyExistsException("Username");
        }
        if (userService.existsByEmailAddress(request.getEmailAddress())) {
            throw new FieldAlreadyExistsException("Email address");
        }
        if (userService.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new FieldAlreadyExistsException("Phone number");
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return userService.save(request);

    }

    public LoginResponse login(LoginRequest loginRequest) {
        try{
        User user = userService.findByUsername(loginRequest.getUsername());
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        String jwtToken = jwtUtil.generateToken(loginRequest.getUsername());

        return LoginResponse.builder()
                .emailAddress(user.getEmailAddress())
                .fullName(user.getFullName())
                .jwtToken(jwtToken)
                .phoneNumber(user.getPhoneNumber())
                .roles(user.getRoles())
                .build();
        }catch(Exception ex) {
            ex.printStackTrace();
            return new LoginResponse();

        }

    }

}
