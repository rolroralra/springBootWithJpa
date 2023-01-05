package com.example.springbootwithjpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.example.springbootwithjpa.domain.Order;
import com.example.springbootwithjpa.repository.common.JpaRepositoryTest;
import com.example.springbootwithjpa.repository.data.OrderDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

class OrderRepositoryTest extends JpaRepositoryTest<Order, Long> {

    @Autowired
    private OrderRepository repository;

    @Override
    protected JpaRepository<Order, Long> repository() {
        return repository;
    }

    @Override
    protected Order createTestInstance() {
        return OrderDataSet.testData();
    }
}
