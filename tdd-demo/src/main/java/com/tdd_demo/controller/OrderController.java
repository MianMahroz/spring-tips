package com.tdd_demo.controller;

import com.tdd_demo.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }




    @GetMapping("/user/{id}")
    public ResponseEntity<?> getTotalOrderAmountByUserId(@PathVariable("id") long id){

        return ResponseEntity.ok(orderService.calculateTotalOrderAmountByUserId(18910));
    }

}
