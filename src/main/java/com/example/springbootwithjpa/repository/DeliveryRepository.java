package com.example.springbootwithjpa.repository;

import com.example.springbootwithjpa.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

}
