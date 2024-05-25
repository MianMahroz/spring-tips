package com.tdd_demo.service;

import com.tdd_demo.records.ProductOrder;
import com.tdd_demo.repo.CartRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public List<ProductOrder> getCartItemsByUserId(long userId) {
        return cartRepository.getCart()
                .stream()
                .filter(c->c.userId()==userId)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
