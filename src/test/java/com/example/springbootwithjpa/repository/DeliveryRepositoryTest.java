package com.example.springbootwithjpa.repository;

import com.example.springbootwithjpa.domain.CategoryItem;
import com.example.springbootwithjpa.domain.Delivery;
import com.example.springbootwithjpa.repository.common.JpaRepositoryTest;
import com.example.springbootwithjpa.repository.data.CategoryDataSet;
import com.example.springbootwithjpa.repository.data.DeliveryDataSet;
import com.example.springbootwithjpa.repository.data.ItemDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

class DeliveryRepositoryTest extends JpaRepositoryTest<Delivery, Long> {

    @Autowired
    private DeliveryRepository repository;

    @Override
    protected JpaRepository<Delivery, Long> repository() {
        return repository;
    }

    @Override
    protected Delivery createTestInstance() {
        return DeliveryDataSet.testData();
    }
}
