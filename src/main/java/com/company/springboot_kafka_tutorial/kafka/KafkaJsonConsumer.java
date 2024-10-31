package com.company.springboot_kafka_tutorial.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.company.springboot_kafka_tutorial.models.User;

@Service
public class KafkaJsonConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaJsonConsumer.class);

    @Value("${spring.kafka.topic.json}")
    private String topicName;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @KafkaListener(topics = "${spring.kafka.topic.json}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(User user) {
        LOGGER.info(String.format("Topic %s | Consumed JSON message -> %s", topicName, user.toString()));
    }
}
