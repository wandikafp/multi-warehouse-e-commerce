package com.anw.product.service.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ProductBaseResponse {
    private final UUID id;
    private final String name;
    private final String description;
    private final double price;
    private final int stockQuantity;
    private final String imageUrl;
    private final CategoryResponse category;
}
