package com.anw.order.service.domain.dto;

import com.anw.domain.valueobject.Money;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CartItemCommand {
    private UUID id;
    private UUID productId;
    private final String name;
    private BigDecimal price;
    private final int stock;
    private final String imgUrl;
    private int quantity;
    private BigDecimal subTotal;
}
