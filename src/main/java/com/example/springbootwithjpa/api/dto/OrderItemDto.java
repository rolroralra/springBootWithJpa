package com.example.springbootwithjpa.api.dto;

import com.example.springbootwithjpa.domain.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    @JsonIgnore
    private Long orderId;
    private String itemName;
    private Long orderPrice;
    private Long count;

    public static  OrderItemDto of(OrderItem orderItem) {
        return new OrderItemDto(orderItem.getOrderId(), orderItem.getItemName(), orderItem.getOrderPrice(), orderItem.getCount());
    }
}
