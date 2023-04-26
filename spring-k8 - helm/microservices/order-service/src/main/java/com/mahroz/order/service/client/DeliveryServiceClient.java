package com.mahroz.order.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "delivery-service",url = "http://delivery:8083")
public interface DeliveryServiceClient {

    @GetMapping("/processDelivery")
    ResponseEntity<String> processOrderDelivery();
}
