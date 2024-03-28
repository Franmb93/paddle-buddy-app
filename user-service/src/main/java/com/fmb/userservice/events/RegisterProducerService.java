package com.fmb.userservice.events;

import com.fmb.userservice.models.User;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterProducerService {

    private final KafkaTemplate<String, User> kafkaTemplate;

    public void sendUserRegistered(String topic, User userMessage) {
        kafkaTemplate.send(topic, userMessage);
    }
}
