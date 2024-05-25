package com.tdd_demo.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.tdd_demo.records.ProductOrder;
import com.tdd_demo.records.Product;
import com.tdd_demo.repo.CartRepository;
import com.tdd_demo.repo.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    private OrderServiceImpl orderService;


    @BeforeEach
    public void beforeEach() {

        MockitoAnnotations.openMocks(this);
        orderService = new OrderServiceImpl(new ProductServiceImpl(productRepository), new CartServiceImpl(cartRepository));
    }

    @Test
    public void calculateTotalBundleAmountByUserId_test() {

        List<ProductOrder> cart = new ArrayList<>(List.of(
                new ProductOrder(18910, "#123456", 66L, 1),
                new ProductOrder(18910, "#123456", 77L, 1),
                new ProductOrder(18910, "#123456", 88L, 1)
        ));

        List<Product> products = List.of(
                new Product(66L, "PEPSI", 10),
                new Product(77L, "LAYS", 20),
                new Product(88L, "COKE", 12)
        );

        // when
        when(cartRepository.getCart()).thenReturn(cart);
        when(productRepository.getProducts()).thenReturn(products);


        var totalAmount = orderService.calculateTotalOrderAmountByUserId(18910);


        assertEquals(39, totalAmount);

    }
}
