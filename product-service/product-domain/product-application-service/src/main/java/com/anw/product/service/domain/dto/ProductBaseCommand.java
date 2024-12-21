package com.anw.product.service.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class ProductBaseCommand {
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private double price;
    @NotNull
    private int stockQuantity;
    @NotNull
    private String imageUrl;
    @NotNull
    private UUID categoryId;
}
