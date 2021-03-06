package com.evampsaanga.kafkalearning.kafka;

import com.evampsaanga.kafkalearning.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumer.class);
    @KafkaListener(topics = "topic-example-json",groupId = "myGroup")
    public void consume(User user){

        LOGGER.info(String.format("Json Message Received -> %s",user.toString()));

    }
}
