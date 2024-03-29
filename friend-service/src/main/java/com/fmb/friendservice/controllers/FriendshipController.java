package com.fmb.friendservice.controllers;

import com.fmb.friendservice.models.Friendships;
import com.fmb.friendservice.repositories.FriendshipRepository;
import com.fmb.friendservice.requests.FriendshipRequest;
import com.fmb.friendservice.services.FriendshipService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
public class FriendshipController {

    private final FriendshipService friendshipService;

    @PostMapping("/friendship")
    public ResponseEntity<Friendships> sendFriendRequest(@RequestBody FriendshipRequest friendshipRequest) {
        log.info("Controller sendFriendRequest: " + friendshipRequest);

        Friendships createdFriendship = friendshipService.sendFriendRequest(friendshipRequest.getUserId(), friendshipRequest.getFriendId());
        return new ResponseEntity<>(createdFriendship, HttpStatus.OK);
    }
}
