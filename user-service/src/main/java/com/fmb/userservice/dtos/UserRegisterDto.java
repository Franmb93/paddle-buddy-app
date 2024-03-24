package com.fmb.userservice.dtos;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class UserRegisterDto extends UserDto{
    private String password;
}
