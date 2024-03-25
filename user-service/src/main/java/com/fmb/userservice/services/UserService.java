package com.fmb.userservice.services;

import com.fmb.userservice.dtos.UserDto;
import com.fmb.userservice.dtos.UserRegisterDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    UserDto saveUser(UserRegisterDto userDto);

    void deleteUser(Long id);

    UserDto updateUser(UserDto userDto);
}
