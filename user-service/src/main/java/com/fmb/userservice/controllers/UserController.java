package com.fmb.userservice.controllers;

import com.fmb.userservice.dtos.ErrorDto;
import com.fmb.userservice.dtos.UserDto;
import com.fmb.userservice.dtos.UserRegisterDto;
import com.fmb.userservice.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);

        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/user")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterDto userRegisterDto) {

        try {
            UserDto user = userService.saveUser(userRegisterDto);

            log.debug("User registered properly" + user.toString());
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (IllegalStateException e) {
            log.debug("Tried to register a new user, but it already exists; email: " + userRegisterDto.getEmail());
            return new ResponseEntity<>(ErrorDto.builder().message(e.getLocalizedMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
