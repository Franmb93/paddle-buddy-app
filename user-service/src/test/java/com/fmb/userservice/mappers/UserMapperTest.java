package com.fmb.userservice.mappers;

import com.fmb.userservice.dtos.UserDto;
import com.fmb.userservice.models.User;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void toDto() {
        User user = User.builder().name("John Doe").email("JohnDoe@gmail.com").password("123456").build();

        UserDto userDto = userMapper.toDto(user);
        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getName(), user.getName());
        assertEquals(userDto.getEmail(), user.getEmail());
    }

    @Test
    void toEntity() {
        UserDto userDto = UserDto.builder().name("John Doe").email("JohnDoe@gmail.com").build();

        User user = userMapper.toEntity(userDto);

        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getName(), user.getName());
        assertEquals(userDto.getEmail(), user.getEmail());
    }
}