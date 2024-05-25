package com.tdd_demo.service;

import com.tdd_demo.records.Product;
import com.tdd_demo.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProductDetailsByIds(List<Long> productIdList) {

        return productRepository.getProducts()
                .stream()
                .filter(p->productIdList.contains(p.Id()))
                .toList();
    }
}
