package com.mahroz.shippingservice.kafka;


import com.mahroz.testcontainers.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumerService  {

    private final String consumerTopic = "new-orders";

    @KafkaListener(topics = consumerTopic ,groupId = "my-group")
    public void onNewOrder(@Payload OrderDto orderDto){
        log.info("NEW ORDER RECEIVED");
        log.info(orderDto.toString());
    }


}
