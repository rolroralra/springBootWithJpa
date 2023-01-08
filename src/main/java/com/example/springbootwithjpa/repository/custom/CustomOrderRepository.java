package com.example.springbootwithjpa.repository.custom;

import com.example.springbootwithjpa.api.dto.SimpleOrderDto;
import com.example.springbootwithjpa.controller.dto.OrderSearchDto;
import com.example.springbootwithjpa.domain.Order;
import java.util.List;

public interface CustomOrderRepository {
    List<Order> findAll(OrderSearchDto orderSearchDto);
    List<SimpleOrderDto> findAllOrderDtos();
}
