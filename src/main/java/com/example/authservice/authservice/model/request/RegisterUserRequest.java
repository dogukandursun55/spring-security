package com.example.authservice.authservice.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.authservice.authservice.validation.PhoneNumber.PhoneNumber;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserRequest {
     
    @JsonProperty("username")
    @NotBlank
    private String username;

    @JsonProperty("password")
    @NotBlank
    @Size(min = 8, max = 15, message = "Password must be between 8-15 characters.")
    private String password;

    @JsonProperty("full_name")
    @NotBlank
    private String fullName;

    @JsonProperty("email_address")
    @Email
    @NotBlank
    private String emailAddress;

    @JsonProperty("phone_number")
    @NotBlank
    @PhoneNumber
    private String phoneNumber;
}
