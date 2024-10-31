package com.company.springboot_kafka_tutorial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.springboot_kafka_tutorial.kafka.KafkaProducer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.util.StringUtils;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/v1/kafka")
@Tag(name = "Kafka Message Controller", description = "API endpoints for kafka message operations")
public class MessageController {
    private KafkaProducer kafkaProducer;

    @Autowired
    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @Operation(summary = "Publish message to Kafka topic", description = "Sends a message to the configured Kafka topic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message successfully sent"),
            @ApiResponse(responseCode = "400", description = "Invalid message provided")
    })
    @GetMapping("/publish")
    public ResponseEntity<String> publish(
            @Parameter(description = "Message to be published", required = true) @RequestParam("message") String message) {

        if (!StringUtils.hasText(message)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Message cannot be empty or null");
        }

        if (message.length() > 1000) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Message length cannot exceed 1000 characters");
        }

        kafkaProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent to the topic");
    }
}