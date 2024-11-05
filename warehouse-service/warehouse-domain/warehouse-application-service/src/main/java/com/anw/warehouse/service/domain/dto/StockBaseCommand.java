package com.anw.warehouse.service.domain.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class StockBaseCommand {
    private UUID warehouseId;
    private UUID productId;
    private Integer amount;
}
