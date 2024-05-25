package com.tdd_demo.service;

import com.tdd_demo.records.Bundle;
import com.tdd_demo.records.ProductOrder;
import com.tdd_demo.records.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private double totalBundledProductsAmount = 0.0;
    private double totalNonBundledProductsAmount = 0.0;

    private final ProductService productService;
    private final CartService cartService;

    List<Bundle> bundleRepo = List.of(
            new Bundle(1, List.of(66L, 77L), 10.0),
            new Bundle(1, List.of(88L, 77L), 20.0)
    );

    public OrderServiceImpl(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @Override
    public double calculateTotalOrderAmountByUserId(long userId) {
        totalBundledProductsAmount = 0.0;
        totalNonBundledProductsAmount = 0.0;

        List<ProductOrder> orders = cartService.getCartItemsByUserId(userId);
        var orderProductDetails = productService.getProductDetailsByIds(orders.stream().map(ProductOrder::productId).toList());



        bundleRepo.forEach(bundle -> {
            var matchOrders = orders
                    .stream()
                    .filter(order -> bundle.productIds()
                            .contains(order.productId()))
                    .toList();

            if (!matchOrders.isEmpty() && matchOrders.size() > 1) { // at least two products

                var priceWithoutDiscount = orderProductDetails
                        .stream()
                        .filter(p -> matchOrders
                                .stream()
                                .map(ProductOrder::productId)
                                .toList()
                                .contains(p.Id())).mapToDouble(Product::price).sum();
                System.out.println("without discount=" + priceWithoutDiscount);

                var priceAfterDiscount = priceWithoutDiscount - (priceWithoutDiscount * bundle.discount() / 100);
                System.out.println("with discount=" + priceAfterDiscount);

                totalBundledProductsAmount = priceAfterDiscount;


                /*
                 * pepsi + lay  = 10 + 20 = 30  if bundle found give 10% discount = 27
                 * remove pepsi and lays from order ,  to prevent i.e lays + coke
                 * Because lays is already bundled with pepsi and availed discount
                 */

                // remove matched orders from list
                orders.removeIf(o -> matchOrders
                        .stream()
                        .map(ProductOrder::productId).toList()
                        .contains(o.productId()));
            }

        });


        // calculating order amount for none bundled orders
        orders.forEach(o -> totalNonBundledProductsAmount = orderProductDetails
                .stream()
                .filter(p -> p.Id() == o.productId())
                .mapToDouble(p -> p.price() * o.qty()).sum());

        return totalBundledProductsAmount + totalNonBundledProductsAmount;
    }
}
