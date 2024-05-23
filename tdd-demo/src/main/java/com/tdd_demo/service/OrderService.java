package com.tdd_demo.service;

import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    public double calculateTotalOrderAmountByUserId(long userId);
}
