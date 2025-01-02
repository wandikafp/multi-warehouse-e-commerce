package com.anw.order.service.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class CartItemResponse {
    private UUID id;
    private UUID productId;
    private final String name;
    private BigDecimal price;
    private final int stock;
    private final String imgUrl;
    private int quantity;
    private BigDecimal subTotal;
}
