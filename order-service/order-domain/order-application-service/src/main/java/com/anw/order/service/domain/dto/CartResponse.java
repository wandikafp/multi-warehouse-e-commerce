package com.anw.order.service.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class CartResponse {
    private UUID customerId;
    private List<CartItemResponse> cartItems;
    private String totalPrice;
}
