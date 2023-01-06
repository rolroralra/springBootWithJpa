package com.example.springbootwithjpa.controller.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderCreateDto {
    @NotNull
    private Long memberId;

    @NotNull
    private Long itemId;

    @NotNull
    private Long count;
}
