package com.fmb.userservice.controllers;

import com.fmb.userservice.dtos.LoginDto;
import com.fmb.userservice.responses.LoginResponse;
import com.fmb.userservice.services.LoginService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDto loginDto) {
        try {
            boolean success = loginService.login(loginDto);

            log.debug("Login success: " + loginDto.getEmail());

            return new ResponseEntity<>(LoginResponse.builder().success(success).build(), HttpStatus.OK);

        } catch (IllegalStateException e) {
            log.debug("Something went wrong while login");

            return new ResponseEntity<>(LoginResponse.builder().success(false).message(e.getLocalizedMessage()).build(), HttpStatus.UNAUTHORIZED);
        }
    }
}
