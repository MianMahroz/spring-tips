package com.mahroz.testcontainers.repo;

import com.mahroz.testcontainers.domain.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<ProductOrder,Long> {
}
