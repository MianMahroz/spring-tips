package com.mahroz.testcontainers.services;


import com.mahroz.testcontainers.config.EnvConfig;
import com.mahroz.testcontainers.domain.ProductOrder;
import com.mahroz.testcontainers.dto.OrderDto;
import com.mahroz.testcontainers.kafka.KafkaProducerService;
import com.mahroz.testcontainers.repo.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl extends EnvConfig implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    public void saveOrder(OrderDto orderDto) {
        var obj = new ProductOrder();
        obj.setProductId(orderDto.productId());
        obj.setQty(orderDto.qty());
        orderRepository.save(obj);
        log.info("ORDER SAVED SUCCESSFULLY");
    }

    @Override
    public List<ProductOrder> getOrders() {
        return orderRepository.findAll();
    }
}
