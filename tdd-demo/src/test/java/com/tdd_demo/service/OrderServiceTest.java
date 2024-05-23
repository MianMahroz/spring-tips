package com.tdd_demo.service;


import static org.junit.jupiter.api.Assertions.*;

import com.tdd_demo.records.Bundle;
import com.tdd_demo.records.ProductOrder;
import com.tdd_demo.records.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceTest {



    private OrderService orderService;


    @BeforeEach
    public void beforeEach() {

        MockitoAnnotations.openMocks(this);
        orderService = new OrderServiceImpl();
    }

    @Test
    public void calculateTotalBundleAmountByUserId_test() {


        var orders = new ArrayList<ProductOrder>();

        orders.add(new ProductOrder(18910, "#123456", 66L, 1));
        orders.add(new ProductOrder(18910, "#123456", 77L, 1));
        orders.add(new ProductOrder(18910, "#123456", 99L, 1));

        var totalAmount = orderService.calculateTotalOrderAmountByUserId(18910);



        assertEquals(39, totalAmount);

    }
}
