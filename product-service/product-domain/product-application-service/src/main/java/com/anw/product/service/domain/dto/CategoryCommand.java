package com.anw.product.service.domain.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CategoryCommand {
    private UUID id;
    private String name;
}
