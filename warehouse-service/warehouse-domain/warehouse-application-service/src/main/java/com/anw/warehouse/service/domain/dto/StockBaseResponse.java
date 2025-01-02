package com.anw.warehouse.service.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class StockBaseResponse {
    private UUID id;
    private UUID warehouseId;
    private UUID productId;
    private Integer quantity;
}