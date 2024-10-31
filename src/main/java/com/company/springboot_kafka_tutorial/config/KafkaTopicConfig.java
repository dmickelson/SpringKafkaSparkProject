package com.company.springboot_kafka_tutorial.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.topic.json}")
    private String jsonTopicName;

    @Value("${spring.kafka.topic.string}")
    private String stringTopicName;

    @Value("${spring.kafka.topic.partitions}")
    private int partitions;

    @Value("${spring.kafka.topic.replication-factor}")
    private int replicationFactor;

    @Bean
    public NewTopic createJsonTopic() {
        return TopicBuilder.name(jsonTopicName)
                .partitions(partitions)
                .replicas(replicationFactor)
                .build();
    }

    @Bean
    public NewTopic createStringTopic() {
        return TopicBuilder.name(stringTopicName)
                .partitions(partitions)
                .replicas(replicationFactor)
                .build();
    }
}