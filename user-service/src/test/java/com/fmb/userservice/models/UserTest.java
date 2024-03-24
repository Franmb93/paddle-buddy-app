package com.fmb.userservice.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserGettersAndSetters() {
        User user = User.builder().build();

        user.setEmail("fmb@test.es");
        user.setName("FMB");

        assertEquals(user.getEmail(), "fmb@test.es");
        assertEquals(user.getName(), "FMB");
    }
}