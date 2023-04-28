package com.mahroz.delivery.service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/delivery")
public class DeliveryController {

    @GetMapping("/processDelivery")
    public ResponseEntity<String> processOrderDelivery(){
        log.info("Process Order Delivery Initiated");
        return new ResponseEntity<>("Order Delivery Scheduled", HttpStatus.OK);
    }
}
