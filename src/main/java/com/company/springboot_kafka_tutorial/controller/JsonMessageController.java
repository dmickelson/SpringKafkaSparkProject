package com.company.springboot_kafka_tutorial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.springboot_kafka_tutorial.kafka.KafkaJsonProducer;
import com.company.springboot_kafka_tutorial.models.User;

@RestController
@RequestMapping("/api/v1/kafka")
public class JsonMessageController {

    private KafkaJsonProducer kafkaJsonProducer;

    @Autowired
    public JsonMessageController(KafkaJsonProducer kafkaJsonProducer) {
        this.kafkaJsonProducer = kafkaJsonProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody User user) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("User object cannot be null");
        }

        if (user.getId() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("User ID cannot be null and must be greater than 0");
        }

        if (user.getFirstName() != null && user.getFirstName().length() > 100) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("First name cannot be null or exceed 100 characters");
        }

        if (user.getLastName() != null && user.getLastName().length() > 100) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Last name cannot be null or exceed 100 characters");
        }

        kafkaJsonProducer.sendMessage(user);
        return ResponseEntity.ok("JSON message sent to Kafka topic");
    }

}
