package com.anw.order.service.domain.dto;

import lombok.Data;

@Data
public class CartItemCommand {
    private Long productId;
    private int quantity;
    private double price;
}
