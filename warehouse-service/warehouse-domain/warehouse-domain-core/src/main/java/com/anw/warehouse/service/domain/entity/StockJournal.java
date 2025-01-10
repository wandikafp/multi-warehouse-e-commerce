package com.anw.warehouse.service.domain.entity;

import com.anw.domain.entity.BaseEntity;
import com.anw.domain.valueobject.ProductId;
import com.anw.domain.valueobject.StockId;
import com.anw.domain.valueobject.WarehouseId;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class StockJournal extends BaseEntity<UUID> {
    private final StockId stockId;
    private final WarehouseId warehouseId;
    private final ProductId productId;
    private final Integer quantityChange;
    private final String reason;

    @Builder
    public StockJournal(UUID id, StockId stockId, WarehouseId warehouseId, ProductId productId, Integer quantityChange, String reason) {
        this.setId(id);
        this.stockId = stockId;
        this.warehouseId = warehouseId;
        this.productId = productId;
        this.quantityChange = quantityChange;
        this.reason = reason;
    }
}
