package com.fmb.userservice.services.impl;

import com.fmb.userservice.dtos.UserDto;
import com.fmb.userservice.dtos.UserRegisterDto;
import com.fmb.userservice.mappers.UserMapper;
import com.fmb.userservice.models.Role;
import com.fmb.userservice.models.User;
import com.fmb.userservice.repository.RoleRepository;
import com.fmb.userservice.repository.UserRepository;
import com.fmb.userservice.requests.UserChangePasswordRequest;
import com.fmb.userservice.services.RoleService;
import com.fmb.userservice.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

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
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role userRole = roleService.getRoleByName("USER");
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        user.setRoles(roles);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public void changePassword(UserChangePasswordRequest changePasswordRequest) {
        log.debug("Changing password: " + changePasswordRequest.toString());

        // TODO: Refactor to a customized exception. (TODO: PasswordChangeRequestException)
        User user = userRepository.findByEmail(changePasswordRequest.getEmail()).orElseThrow(() -> new IllegalStateException("User not found."));

        if (! passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Email or password doesn't match our records");
        }

        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> userToDelete = userRepository.findById(id);

        // TODO: Refactor to soft-delete. Delete password when soft deleted.
        userToDelete.ifPresent(userRepository::delete);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user = userRepository.save(user);

        return userMapper.toDto(user);
    }
}
