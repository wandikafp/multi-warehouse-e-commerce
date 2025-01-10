package com.anw.order.service.domain.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CartItemCommand {
    private UUID id;
    private UUID productId;
    private String name;
    private BigDecimal price;
    private int stock;
    private String imgUrl;
    private int quantity;
    private BigDecimal subTotal;
}
