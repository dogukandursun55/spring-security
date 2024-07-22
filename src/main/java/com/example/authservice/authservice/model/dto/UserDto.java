package com.example.authservice.authservice.model.dto;

import java.util.Set;

import com.example.authservice.authservice.domain.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;

    private String username;

    private String password;

    private String fullName;

    private String emailAddress;

    private String phoneNumber;

    private Set<Role> roles;
}
