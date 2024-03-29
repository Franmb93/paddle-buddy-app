package com.fmb.friendservice.producers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FriendshipKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.friendshipRequest}")
    private String topic;

    public FriendshipKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendFriendRequestNotification(String message) {
        kafkaTemplate.send(topic, message);
        log.info("[KAFKA] Message sent: " + "[topic=" + topic + "], [message=" + message + "]");
    }
}
