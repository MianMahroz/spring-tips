package com.mahroz.testcontainers.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="orders")
@Getter
@Setter
@NoArgsConstructor
public class ProductOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name="product_id")
    private int productId;

    @Column
    private int qty;
}
