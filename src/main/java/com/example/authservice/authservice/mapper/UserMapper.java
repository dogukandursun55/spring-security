package com.example.authservice.authservice.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.example.authservice.authservice.domain.User;
import com.example.authservice.authservice.model.dto.UserDto;
import com.example.authservice.authservice.model.request.RegisterUserRequest;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public UserDto toDto(User user);
    public User toEntity(UserDto userDto);
    public User toEntity(RegisterUserRequest request);
}
