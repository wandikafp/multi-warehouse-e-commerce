package com.anw.order.service.domain.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CartResponse {
    private Long cartId;
    private UUID customerId;
    private List<CartItemResponse> cartItems;
    private double totalPrice;
}
