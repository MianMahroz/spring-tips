package com.mahroz.order.service.controller;

import com.mahroz.order.service.client.DeliveryServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {
    private final DeliveryServiceClient deliveryServiceClient;

    public OrderController(DeliveryServiceClient deliveryServiceClient) {
        this.deliveryServiceClient = deliveryServiceClient;
    }



    @PostMapping("/order")
    public ResponseEntity<String> createOrder(){
        log.info("NEW ORDER RECEIVED");
        deliveryServiceClient.processOrderDelivery();
        return new ResponseEntity<>("Order Created Successfully", HttpStatus.OK);
    }
}
