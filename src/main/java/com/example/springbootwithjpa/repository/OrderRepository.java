package com.example.springbootwithjpa.repository;

import com.example.springbootwithjpa.domain.Order;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("orderRepository")
@Primary
public interface OrderRepository extends JpaRepository<Order, Long> {
}
