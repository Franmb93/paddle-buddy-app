package com.fmb.userservice.controllers;

import com.fmb.userservice.dtos.UserDto;
import com.fmb.userservice.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllusers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
