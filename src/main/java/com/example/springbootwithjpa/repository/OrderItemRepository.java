package com.example.springbootwithjpa.repository;

import com.example.springbootwithjpa.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
