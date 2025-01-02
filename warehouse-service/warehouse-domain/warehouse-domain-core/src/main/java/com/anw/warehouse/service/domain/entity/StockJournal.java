package com.anw.warehouse.service.domain.entity;

import com.anw.domain.entity.BaseEntity;
import com.anw.domain.valueobject.StockId;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class StockJournal extends BaseEntity<UUID> {
    private final StockId stockId;
    private final Integer quantityChange;
    private final String reason;

    @Builder
    public StockJournal(UUID id, StockId stockId, Integer quantityChange, String reason) {
        this.setId(id);
        this.stockId = stockId;
        this.quantityChange = quantityChange;
        this.reason = reason;
    }
}
