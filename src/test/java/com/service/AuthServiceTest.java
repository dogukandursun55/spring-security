package com.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.authservice.authservice.advice.exception.FieldAlreadyExistsException;
import com.example.authservice.authservice.model.dto.UserDto;
import com.example.authservice.authservice.model.request.LoginRequest;
import com.example.authservice.authservice.model.request.RegisterUserRequest;
import com.example.authservice.authservice.repository.UserRepository;
import com.example.authservice.authservice.security.JwtUtil;
import com.example.authservice.authservice.service.AuthService;
import com.example.authservice.authservice.service.UserService;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    @InjectMocks
    private AuthService authService;

    @Mock
    private UserService userService;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void TestRegisterWhenUserExistsThrowsFieldAlreadyExistsException() {
        var mockUserRequest = RegisterUserRequest.builder().username("user").build();
        when(userService.existsByUsername("user")).thenReturn(true);
        assertThrows(FieldAlreadyExistsException.class, () -> authService.registerUser(mockUserRequest));
        verify(userService).existsByUsername(anyString());
    }

    @Test
    public void TestRegisterWhenEmailExistsThrowsFieldAlreadyExistsException() {
        var mockUserRequest = RegisterUserRequest.builder().username("user").emailAddress("email@mail.com").build();
        when(userService.existsByUsername("user")).thenReturn(false);
        when(userService.existsByEmailAddress("email@mail.com")).thenReturn(true);
        assertThrows(FieldAlreadyExistsException.class, () -> authService.registerUser(mockUserRequest));
        verify(userService).existsByUsername(anyString());
        verify(userService).existsByEmailAddress(anyString());
    }

    @Test
    public void TestRegisterWhenPhoneNumberExistsThrowsFieldAlreadyExistsException() {
        var mockUserRequest = RegisterUserRequest.builder()
                .username("user")
                .emailAddress("email@mail.com")
                .phoneNumber("5333333333")
                .build();

        when(userService.existsByUsername("user")).thenReturn(false);
        when(userService.existsByEmailAddress("email@mail.com")).thenReturn(false);
        when(userService.existsByPhoneNumber("5333333333")).thenReturn(true);
        assertThrows(FieldAlreadyExistsException.class, () -> authService.registerUser(mockUserRequest));
        verify(userService).existsByUsername(anyString());
        verify(userService).existsByEmailAddress(anyString());
        verify(userService).existsByPhoneNumber(anyString());

    }

    @Test
    public void TestRegisterWhenSuccessfulReturnsUserDto() {
        var mockUserRequest = RegisterUserRequest.builder()
                .username("user")
                .emailAddress("email@mail.com")
                .phoneNumber("5333333333")
                .password("password")
                .build();

        var expectedResponse = UserDto.builder()
                .username("user")
                .emailAddress("email@mail.com")
                .phoneNumber("5333333333")
                .password("encodedpassword").build();

        when(userService.existsByUsername("user")).thenReturn(false);
        when(userService.existsByEmailAddress("email@mail.com")).thenReturn(false);
        when(userService.existsByPhoneNumber("5333333333")).thenReturn(false);
        when(passwordEncoder.encode("password")).thenReturn("encodedpassword");
        when(userService.save(mockUserRequest)).thenReturn(expectedResponse);  
        var actualResponse = authService.registerUser(mockUserRequest);                                                                     
        assertEquals(expectedResponse,  actualResponse);
        verify(userService).existsByUsername(anyString());
        verify(userService).existsByEmailAddress(anyString());
        verify(userService).existsByPhoneNumber(anyString());
        verify(passwordEncoder).encode(anyString());
        verify(userService).save(any(RegisterUserRequest.class));
    }
}
