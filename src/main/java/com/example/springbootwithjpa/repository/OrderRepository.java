package com.example.springbootwithjpa.repository;

import com.example.springbootwithjpa.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
