package com.example.authservice.authservice.mapper;

import com.example.authservice.authservice.domain.Role;
import com.example.authservice.authservice.domain.User;
import com.example.authservice.authservice.domain.User.UserBuilder;
import com.example.authservice.authservice.model.dto.UserDto;
import com.example.authservice.authservice.model.dto.UserDto.UserDtoBuilder;
import com.example.authservice.authservice.model.request.RegisterUserRequest;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-22T20:51:25+0300",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 3.39.0.v20240620-1855, environment: Java 17.0.11 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDtoBuilder userDto = UserDto.builder();

        userDto.emailAddress( user.getEmailAddress() );
        userDto.fullName( user.getFullName() );
        userDto.id( user.getId() );
        userDto.password( user.getPassword() );
        userDto.phoneNumber( user.getPhoneNumber() );
        Set<Role> set = user.getRoles();
        if ( set != null ) {
            userDto.roles( new HashSet<Role>( set ) );
        }
        userDto.username( user.getUsername() );

        return userDto.build();
    }

    @Override
    public User toEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.emailAddress( userDto.getEmailAddress() );
        user.fullName( userDto.getFullName() );
        user.id( userDto.getId() );
        user.password( userDto.getPassword() );
        user.phoneNumber( userDto.getPhoneNumber() );
        Set<Role> set = userDto.getRoles();
        if ( set != null ) {
            user.roles( new HashSet<Role>( set ) );
        }
        user.username( userDto.getUsername() );

        return user.build();
    }

    @Override
    public User toEntity(RegisterUserRequest request) {
        if ( request == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.emailAddress( request.getEmailAddress() );
        user.fullName( request.getFullName() );
        user.password( request.getPassword() );
        user.phoneNumber( request.getPhoneNumber() );
        user.username( request.getUsername() );

        return user.build();
    }
}
