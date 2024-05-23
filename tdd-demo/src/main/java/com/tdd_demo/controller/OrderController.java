package com.tdd_demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getTotalOrderAmountByUserId(@PathVariable("id") long id){

        return ResponseEntity.ok(null);
    }

}
