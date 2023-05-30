package com.mahroz.order.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "delivery-service",url = "http://localhost:8083")
//@FeignClient(name = "DELIVERY-SERVICE")
public interface DeliveryServiceClient {

    @GetMapping("/delivery/processDelivery")
    ResponseEntity<String> processOrderDelivery();
}
