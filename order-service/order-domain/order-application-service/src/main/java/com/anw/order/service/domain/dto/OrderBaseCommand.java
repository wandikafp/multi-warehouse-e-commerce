package com.anw.order.service.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class OrderBaseCommand {
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
