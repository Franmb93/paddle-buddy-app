package com.fmb.userservice.seeders;

import com.fmb.userservice.models.User;
import com.fmb.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Slf4j
@Component
@Profile("dev")
public class UserSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {
            User user1 = User.builder().name("fmunozbetanzos").email("fcomunozbetanzos@gmail.com").password(passwordEncoder.encode("123456")).build();
            User user2 = User.builder().name("pacomunoz").email("pmunoz@gmail.com").password(passwordEncoder.encode("123456")).build();

            log.info("User 1" + user1);
            log.info("User 2" + user2);

            userRepository.save(user1);
            userRepository.save(user2);

            log.info("!! Saved users without any problem");
        }
    }
}
