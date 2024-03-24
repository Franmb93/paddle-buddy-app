package com.fmb.userservice.repository;

import com.fmb.userservice.models.User;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private  UserRepository userRepository;

    @Test
    void whenSaveUser_thenRetrieveUser() {
        User savedUser = userRepository.save(User.builder()
                .name("John Doe")
                .email("JohnDoe@gmail.com")
                .build());

        User retrievedUser = userRepository.findById(savedUser.getId()).orElse(null);

        assertNotNull(retrievedUser);
        assertEquals(retrievedUser.getName(), "John Doe");
    }
}