package com.example.springbootwithjpa.api.dto;

import com.example.springbootwithjpa.domain.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {
    private String itemName;
    private Long orderPrice;
    private Long count;

    public static  OrderItemDto of(OrderItem orderItem) {
        return new OrderItemDto(orderItem.getItemName(), orderItem.getOrderPrice(), orderItem.getCount());
    }
}
