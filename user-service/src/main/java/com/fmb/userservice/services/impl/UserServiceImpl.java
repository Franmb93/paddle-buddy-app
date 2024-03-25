package com.fmb.userservice.services.impl;

import com.fmb.userservice.dtos.UserDto;
import com.fmb.userservice.dtos.UserRegisterDto;
import com.fmb.userservice.mappers.UserMapper;
import com.fmb.userservice.models.User;
import com.fmb.userservice.repository.UserRepository;
import com.fmb.userservice.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(userMapper::toDto).toList();
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);

        return user.map(userMapper::toDto).orElse(null);
    }

    @Override
    public UserDto saveUser(UserRegisterDto userDto) {
        boolean userExists = userRepository.findByEmail(userDto.getEmail()).isPresent();

        if (userExists) {
            throw new IllegalStateException("User already exists.");
        }

        User user = userMapper.toEntity(userDto);

        // TODO: Codificar la contraseña antes de guardarla
        user.setPassword(userDto.getPassword());

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> userToDelete = userRepository.findById(id);

        userToDelete.ifPresent(userRepository::delete);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user = userRepository.save(user);

        return userMapper.toDto(user);
    }
}
