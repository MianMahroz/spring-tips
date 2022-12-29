package com.mahroz.testcontainers.kafka;


import com.mahroz.testcontainers.config.EnvConfig;
import com.mahroz.testcontainers.dto.OrderDto;
import com.mahroz.testcontainers.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Slf4j
public class KafkaConsumerService {

    private final String consumerTopic = "new-orders";
    private final OrderService orderService;
    public KafkaConsumerService(OrderService  orderService){
        this.orderService = orderService;
    }

    @KafkaListener(topics = consumerTopic ,groupId = "my-group")
    public void onNewOrder(@Payload OrderDto orderDto){
        orderService.saveOrder(orderDto);
        log.info("NEW ORDER RECEIVED");
        log.info(orderDto.toString());

    }


}
