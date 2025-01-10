package com.anw.warehouse.service.domain.event;

import com.anw.domain.event.DomainEvent;
import com.anw.warehouse.service.domain.entity.Stock;
import com.anw.warehouse.service.domain.entity.StockJournal;

import java.time.ZonedDateTime;

public abstract class StockJournalEvent implements DomainEvent<StockJournal> {
    private final StockJournal stockJournal;
    private final ZonedDateTime createdAt;
    public StockJournalEvent(StockJournal stockJournal, ZonedDateTime createdAt) {
        this.stockJournal = stockJournal;
        this.createdAt = createdAt;
    }

    public StockJournal getStockJournal() {
        return stockJournal;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
