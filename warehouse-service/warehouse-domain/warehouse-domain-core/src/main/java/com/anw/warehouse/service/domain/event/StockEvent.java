package com.anw.warehouse.service.domain.event;

import com.anw.domain.event.DomainEvent;
import com.anw.warehouse.service.domain.entity.Stock;
import com.anw.warehouse.service.domain.entity.Warehouse;

import java.time.ZonedDateTime;

public abstract class StockEvent implements DomainEvent<Stock> {
    private final Stock stock;
    private final ZonedDateTime createdAt;
    public StockEvent(Stock stock, ZonedDateTime createdAt) {
        this.stock = stock;
        this.createdAt = createdAt;
    }

    public Stock getStock() {
        return stock;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
