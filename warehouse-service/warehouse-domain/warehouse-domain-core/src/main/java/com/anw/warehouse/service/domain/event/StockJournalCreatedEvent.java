package com.anw.warehouse.service.domain.event;

import com.anw.domain.event.publisher.DomainEventPublisher;
import com.anw.warehouse.service.domain.entity.StockJournal;
import com.anw.warehouse.service.domain.entity.Warehouse;

import java.time.ZonedDateTime;

public class StockJournalCreatedEvent extends StockJournalEvent {
    private final DomainEventPublisher<StockJournalCreatedEvent> stockJournalCreatedEventDomainEventPublisher;

    public StockJournalCreatedEvent(StockJournal stockJournal,
                                    ZonedDateTime createdAt,
                                    DomainEventPublisher<StockJournalCreatedEvent> stockJournalCreatedEventDomainEventPublisher) {
        super(stockJournal, createdAt);
        this.stockJournalCreatedEventDomainEventPublisher = stockJournalCreatedEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        stockJournalCreatedEventDomainEventPublisher.publish(this);
    }
}
