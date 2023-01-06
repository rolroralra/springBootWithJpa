package com.example.springbootwithjpa.repository;

import com.example.springbootwithjpa.domain.OrderItem;
import com.example.springbootwithjpa.repository.common.JpaRepositoryTest;
import com.example.springbootwithjpa.repository.data.OrderItemDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

class OrderItemRepositoryTest extends JpaRepositoryTest<OrderItem, Long> {

    @Autowired
    private OrderItemRepository repository;

    @Override
    protected JpaRepository<OrderItem, Long> repository() {
        return repository;
    }

    @Override
    protected OrderItem createTestInstance() {
        return OrderItemDataSet.testData();
    }
}
