package com.fmb.userservice.services;

import com.fmb.userservice.dtos.UserDto;
import com.fmb.userservice.dtos.UserRegisterDto;
import com.fmb.userservice.requests.UserChangePasswordRequest;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    UserDto saveUser(UserRegisterDto userDto);

    void changePassword(UserChangePasswordRequest changePasswordRequest);

    void deleteUser(Long id);

    UserDto updateUser(UserDto userDto);
}
