package com.tdd_demo.records;

public record ProductOrder(long userId, String orderNumber, long productId, int qty) {
}
