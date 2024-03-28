package com.fmb.userservice.services.impl;

import com.fmb.userservice.dtos.LoginDto;
import com.fmb.userservice.models.User;
import com.fmb.userservice.repository.UserRepository;
import com.fmb.userservice.responses.LoginResponse;
import com.fmb.userservice.services.JwtService;
import com.fmb.userservice.services.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginDto loginDto) {

        User user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(() -> new IllegalStateException("Credentials doesn't match our records"));

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new IllegalStateException("Credentials doesn't match our records");
        }

        String _token = jwtService.generateToken(user.getEmail());

        return LoginResponse.builder().success(true)._token(_token).build();
    }
}
