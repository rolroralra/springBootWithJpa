package com.example.springbootwithjpa.api.dto;

import com.example.springbootwithjpa.domain.Address;
import com.example.springbootwithjpa.domain.Order;
import com.example.springbootwithjpa.domain.OrderStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleOrderDto {
    private Long orderId;

    private String name;

    private LocalDateTime orderDate;

    private OrderStatus orderStatus;

    private Address address;


    public static SimpleOrderDto of(Order order) {
        return new SimpleOrderDto(
                order.getId(),
                order.getMemberName(),
                order.getOrderDate(),
                order.getStatus(),
                order.getDeliveryAddress()
        );

    }
}
