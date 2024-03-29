package com.fmb.friendservice.repositories;

import com.fmb.friendservice.models.Friendships;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendshipRepository extends JpaRepository<Friendships, Long> {
}
