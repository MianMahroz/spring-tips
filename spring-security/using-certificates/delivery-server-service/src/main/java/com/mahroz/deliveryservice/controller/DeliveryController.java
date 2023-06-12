package com.mahroz.deliveryservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/server")
public class DeliveryController {

    @GetMapping
    public String serverData() {
        return "Data from server";
    }


}
