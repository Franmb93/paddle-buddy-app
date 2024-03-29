package com.fmb.userservice.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FriendshipKafkaListener {
    @KafkaListener(topics = "friendship-request", groupId = "fmb-paddle-app")
    public void listenFriendshipRequests(String message) {
        log.info("Received message in user service: {}", message);
    }
}
