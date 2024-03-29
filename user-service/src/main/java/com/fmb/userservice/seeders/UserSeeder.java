package com.fmb.userservice.seeders;

import com.fmb.userservice.models.Role;
import com.fmb.userservice.models.User;
import com.fmb.userservice.repository.RoleRepository;
import com.fmb.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@AllArgsConstructor
@Slf4j
@Component
@Profile("dev")
public class UserSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {
            Role roleAdmin = new Role(1L, "ADMIN");
            Role roleUser = new Role(2L, "USER");

            Set<Role> roles = new HashSet<>();
            Set<Role> rolesOnlyUser = new HashSet<>();

            roleRepository.save(roleAdmin);
            roleRepository.save(roleUser);

            roles.add(roleUser);
            roles.add(roleAdmin);
            rolesOnlyUser.add(roleUser);

            User user1 = User.builder().name("fmunozbetanzos").email("fcomunozbetanzos@gmail.com").password(passwordEncoder.encode("123456")).roles(roles).build();
            User user2 = User.builder().name("pacomunoz").email("pmunoz@gmail.com").password(passwordEncoder.encode("123456")).roles(rolesOnlyUser).build();

            log.info("User 1" + user1);
            log.info("User 2" + user2);

            userRepository.save(user1);
            userRepository.save(user2);

            log.info("!! Saved users without any problem");
        }
    }
}
