# Spring Kafka Example Project

## Overview

This project demonstrates the integration of Spring Boot with Apache Kafka for building event-driven applications. It showcases the basic setup and usage of Kafka producers and consumers using Spring Kafka.

## Features

- Kafka broker and ZooKeeper setup
- Topic creation and management
- Producer and Consumer configuration using Spring Boot
- Event publishing and consumption
- Proper serialization/deserialization setup

## Prerequisites

- Java 8 or higher
- Apache Kafka (extracted to `C:\Kafka` folder on Windows)
- Spring Boot

## Quick Start

### 1. Environment Setup

Download and extract Kafka to `C:\Kafka` folder (required for Windows due to classpath limitations)

### 2. Start Services

Start ZooKeeper:

```bash
bin\windows\zookeeper-server-start.bat config/zookeeper.properties
```

Start Kafka Broker:

```bash
bin\windows\kafka-server-start.bat config/server.properties
```

### 3: CREATE A TOPIC TO STORE YOUR EVENTS

Create a topic to store your events:

```
bin\windows\kafka-topics.bat --create --topic kafka-test --bootstrap-server localhost:9092
```

Verify topic details:

```
bin\windows\kafka-topics.bat --describe --topic kafka-test --bootstrap-server localhost:9092

```

### Testing

Start Console Producer:

```
bin\windows\kafka-console-producer.bat --topic quickstart-events --bootstrap-server localhost:9092
```

Start Console Consumer:

```
bin\windows\kafka-console-consumer.bat --topic kafka-test --from-beginning --bootstrap-server localhost:9092
```

WRITE SOME EVENTS INTO THE TOPIC - RUN THE PRODUCER CONSOLE CLIENT
bin\windows\kafka-console-producer.bat --topic quickstart-events --bootstrap-server localhost:9092

STEP 5: READ THE EVENTS
bin\windows\kafka-console-consumer.bat --topic kafka-test --from-beginning --bootstrap-server localhost:9092

### Application Configuration seen in application.properties

```
spring.kafka.topic=kafka-test
spring.kafka.topic.partitions=1
spring.kafka.topic.replication-factor=1
spring.kafka.properties.session.timeout.ms=45000
spring.kafka.properties.request.timeout.ms=60000
spring.kafka.properties.max.poll.interval.ms=300000
# Producer configuration
spring.kafka.producer.bootstrap-servers=localhost:9092
# Serializer for key and value of the producer
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer
# Consumer configuration
spring.kafka.consumer.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=my-consumer-group
# automatially set the offset to the beginning of the topic offset
spring.kafka.consumer.auto-offset-reset=earliest
# Deserializer for key and value of theconsumer
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer
```

## Configuration

The application.properties file contains all necessary configurations for both Kafka producer and consumer:

- Topic configuration
- Producer settings with serialization
- Consumer settings with deserialization
- Group ID and offset management
- Timeout configurations

## Usage

- Start the ZooKeeper and Kafka services
- Create required topics
- Run the Spring Boot application
- Use the producer endpoints to publish messages
- Messages will be automatically consumed by configured consumers
