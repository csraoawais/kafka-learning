package com.evampsaanga.kafkalearning.controller;

import com.evampsaanga.kafkalearning.kafka.JsonKafkaProducer;
import com.evampsaanga.kafkalearning.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kafka/json")
public class JsonMessageController {
    private JsonKafkaProducer jsonKafkaProducer;

    public JsonMessageController(JsonKafkaProducer jsonKafkaProducer) {
        this.jsonKafkaProducer = jsonKafkaProducer;
    }
    @PostMapping("/publish")
     public ResponseEntity<String> publish(@RequestBody User user){
        jsonKafkaProducer.sendMessage(user);
        return ResponseEntity.ok("Json Sent to the kafka topic");
     }
}
