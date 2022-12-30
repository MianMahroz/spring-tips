package com.mahroz.testcontainers.services;

import com.mahroz.testcontainers.domain.ProductOrder;
import com.mahroz.testcontainers.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    void saveOrder(OrderDto orderDto);
    List<ProductOrder> getOrders();
}
