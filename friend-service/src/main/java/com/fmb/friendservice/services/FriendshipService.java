package com.fmb.friendservice.services;

import com.fmb.friendservice.models.Friendships;

public interface FriendshipService {

    public Friendships sendFriendRequest(Long userId, Long friendId);
}
