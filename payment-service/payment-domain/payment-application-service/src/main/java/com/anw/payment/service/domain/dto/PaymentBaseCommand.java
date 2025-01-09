package com.anw.payment.service.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class PaymentBaseCommand {
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private double price;
    private int stockQuantity;
    @NotNull
    private String imageUrl;
}
