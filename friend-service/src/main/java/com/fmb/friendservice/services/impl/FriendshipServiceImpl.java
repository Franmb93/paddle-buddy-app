package com.fmb.friendservice.services.impl;

import com.fmb.friendservice.enums.FriendshipStatus;
import com.fmb.friendservice.models.Friendships;
import com.fmb.friendservice.models.User;
import com.fmb.friendservice.repositories.FriendshipRepository;
import com.fmb.friendservice.services.FriendshipService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class FriendshipServiceImpl implements FriendshipService {

    private final FriendshipRepository friendshipRepository;

    @Override
    public Friendships sendFriendRequest(Long userId, Long friendId) {
        log.info("Info creating a friendship: [userId=" + userId + "], [friendId=" + friendId + "]");


        Friendships friendships = Friendships.builder()
                .requesterId(userId)
                .addresseeId(friendId)
                .status(FriendshipStatus.PENDING)
                .build();

        log.info("Trying to create friendship: " + friendships);

        return friendshipRepository.save(friendships);
    }
}
