package com.fmb.userservice.services;

import com.fmb.userservice.dtos.UserDto;
import com.fmb.userservice.dtos.UserRegisterDto;
import com.fmb.userservice.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> getAllusers();

    UserDto getUserById(Long id);

    UserDto saveUser(UserRegisterDto userDto);

    void deleteUser(Long id);

    UserDto updateUser(UserDto userDto);
}
