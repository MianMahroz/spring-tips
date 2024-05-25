package com.tdd_demo.repo;

import com.tdd_demo.records.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductRepository {


    public List<Product> getProducts(){

        return new ArrayList<>(List.of(
                new Product(66L, "PEPSI", 10),
                new Product(77L, "LAYS", 20),
                new Product(88L, "COKE", 12)
        ));
    }
}
