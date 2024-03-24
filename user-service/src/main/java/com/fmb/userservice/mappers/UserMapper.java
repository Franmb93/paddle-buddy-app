package com.fmb.userservice.mappers;

import com.fmb.userservice.dtos.UserDto;
import com.fmb.userservice.dtos.UserRegisterDto;
import com.fmb.userservice.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public User toEntity(UserDto dto) {
        return User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail()).build();
    }
}
