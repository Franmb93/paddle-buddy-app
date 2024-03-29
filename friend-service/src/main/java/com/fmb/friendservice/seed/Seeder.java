package com.fmb.friendservice.seed;

import com.fmb.friendservice.models.User;
import com.fmb.friendservice.repositories.FriendshipRepository;
import com.fmb.friendservice.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
@AllArgsConstructor
public class Seeder implements CommandLineRunner {
    private final UserRepository userRepository;
    private final FriendshipRepository friendshipRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (shouldSeed()) {
            seedUsers();
            seedFriendships();
        }
    }

    private boolean shouldSeed() {
        return userRepository.count() == 0;

    }

    private void seedUsers() {
         User user1 = User.builder().id(1L).build();
         userRepository.save(user1);
    }

    private void seedFriendships() {
        // Create and save friendship entities
        // Assuming you have a method to add a friend for a user
        // Example:
        // User user1 = userService.findByUsername("username1");
        // User user2 = userService.findByUsername("username2");
        // friendService.addFriend(user1, user2);
    }
}
