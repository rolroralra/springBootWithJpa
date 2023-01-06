package com.example.springbootwithjpa.controller.dto;

import com.example.springbootwithjpa.domain.OrderStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderSearchDto {
    private final String memberName;

    private final OrderStatus orderStatus;
}
