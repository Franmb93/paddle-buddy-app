package com.fmb.userservice.services.impl;

import com.fmb.userservice.dtos.LoginDto;
import com.fmb.userservice.models.User;
import com.fmb.userservice.repository.UserRepository;
import com.fmb.userservice.services.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public boolean login(LoginDto loginDto) {

        User user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(() -> new IllegalStateException("Credentials doesn't match our records"));

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new IllegalStateException("Credentials doesn't match our records");
        }

        return true;
    }
}
