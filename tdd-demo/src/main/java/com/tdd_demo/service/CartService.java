package com.tdd_demo.service;

import com.tdd_demo.records.ProductOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {

    List<ProductOrder> getCartItemsByUserId(long userId);
}
