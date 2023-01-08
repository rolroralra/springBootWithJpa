package com.example.springbootwithjpa.controller.dto;

import com.example.springbootwithjpa.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderSearchDto {
    private String memberName;

    private OrderStatus orderStatus;
}
