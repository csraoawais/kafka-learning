package com.evampsaanga.kafkalearning.config;

import com.evampsaanga.kafkalearning.kafka.KafkaProducer;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaTopicConfig.class);
    @Bean
    public NewTopic newTopic() {
        LOGGER.info(String.format("Topic created"));
        return TopicBuilder.name("topic-example").build();

    }
    @Bean
    public NewTopic newTopicJson() {
        LOGGER.info(String.format("Topic created"));
        return TopicBuilder.name("topic-example-json").build();

    }
}