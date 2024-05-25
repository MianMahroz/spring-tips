package com.tdd_demo.service;

import com.tdd_demo.records.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProductDetailsByIds(List<Long> productIdList);
}
