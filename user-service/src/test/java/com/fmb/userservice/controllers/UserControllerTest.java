package com.fmb.userservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fmb.userservice.dtos.UserDto;
import com.fmb.userservice.dtos.UserRegisterDto;
import com.fmb.userservice.services.JwtService;
import com.fmb.userservice.services.LoginService;
import com.fmb.userservice.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class UserControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private LoginService loginService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;


    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    void getAllUsers_ReturnsUsersList() throws Exception {
        UserDto user1 = UserDto.builder().id(1L).name("John Doe").email("john@example.com").build();
        UserDto user2 = UserDto.builder().id(1L).name("Jane Doe").email("Jane@example.com").build();

        Mockito.when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/api/v1/all-users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[1].name").value("Jane Doe"));
    }

    @Test
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    void getUserById_ReturnsUser() throws Exception {
        UserDto user = UserDto.builder().id(1L).name("John Doe").email("john@example.com").build();

        Mockito.when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/api/v1/user/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    void getUserById_WhenUserDoesNotExist_ReturnsEmptyResponse() throws Exception {
        Long userId = 1L;

        Mockito.when(userService.getUserById(userId)).thenReturn(null);

        mockMvc.perform(get("/api/v1/user/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));
    }

    @Test
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    void registerUserSuccessfully() throws Exception {
        UserRegisterDto userRegisterDto = new UserRegisterDto(1L, "John dowww", "johndoe@email.com", "123123");
        UserDto userDto = new UserDto(1L, "John dowww", "johndoe@email.com");

        given(userService.saveUser(any(UserRegisterDto.class))).willReturn(userDto);

        mockMvc.perform(post("/api/v1/user").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRegisterDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("johndoe@email.com"));
    }

    @Test
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    void registerUserWhenUserAlreadyExists() throws Exception {
        UserRegisterDto userRegisterDto = new UserRegisterDto(1L, "John dowww", "johndoe@email.com", "123123");
        String errorMessage = "User already exists";

        given(userService.saveUser(any(UserRegisterDto.class))).willThrow(new IllegalStateException(errorMessage));

        mockMvc.perform(post("/api/v1/user").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRegisterDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(errorMessage));
    }
}