package com.mahroz.shippingservice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShippingController {

    @GetMapping("/isAlive")
    public String isAlive(){
        return "YES";
    }

}
