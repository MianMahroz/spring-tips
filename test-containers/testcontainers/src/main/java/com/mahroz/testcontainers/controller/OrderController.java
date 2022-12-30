package com.mahroz.testcontainers.controller;

import com.mahroz.testcontainers.config.EnvConfig;
import com.mahroz.testcontainers.domain.ProductOrder;
import com.mahroz.testcontainers.dto.OrderDto;
import com.mahroz.testcontainers.kafka.KafkaProducerService;
import com.mahroz.testcontainers.services.OrderService;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController extends EnvConfig {

    private final KafkaProducerService kafkaProducerService;
    private final OrderService orderService;

    public OrderController(KafkaProducerService kafkaProducerService,OrderService orderService){
        this.kafkaProducerService = kafkaProducerService;
        this.orderService = orderService;
    }

    @PostMapping("saveOrder")
    public ResponseEntity<String> saveOrder(@RequestBody OrderDto orderDto){
        kafkaProducerService.sendData(shippingTopic,"NEW_ORDERS",orderDto);
        this.orderService.saveOrder(orderDto);
        return new ResponseEntity<>("ORDER SEND TO KAFKA",HttpStatus.OK);
    }


    @GetMapping("/orders")
    public List<ProductOrder> getOrders(){
        return orderService.getOrders();
    }

}
