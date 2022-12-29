package com.mahroz.testcontainers.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String,Object> kafkaTemplate;

    KafkaProducerService(KafkaTemplate<String,Object> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendData(String topic, String key, Object data){
        kafkaTemplate.send(topic,key,data);
    }






}
