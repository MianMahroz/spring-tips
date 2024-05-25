package com.tdd_demo.repo;

import com.tdd_demo.records.ProductOrder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartRepository {

    public List<ProductOrder> getCart(){

        return new ArrayList<>(List.of(
                new ProductOrder(18910, "#123456", 66L, 1),
                new ProductOrder(18910, "#123456", 77L, 1),
                new ProductOrder(18910, "#123456", 88L, 1)
        ));
    }
}
