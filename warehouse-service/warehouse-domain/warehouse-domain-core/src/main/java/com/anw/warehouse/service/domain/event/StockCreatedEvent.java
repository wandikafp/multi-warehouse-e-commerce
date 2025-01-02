package com.anw.warehouse.service.domain.event;

import com.anw.domain.event.publisher.DomainEventPublisher;
import com.anw.warehouse.service.domain.entity.Stock;

import java.time.ZonedDateTime;

public class StockCreatedEvent extends StockEvent {
    private final DomainEventPublisher<StockCreatedEvent> stockCreatedEventDomainEventPublisher;

    public StockCreatedEvent(Stock stock,
                             ZonedDateTime createdAt,
                             DomainEventPublisher<StockCreatedEvent> stockCreatedEventDomainEventPublisher) {
        super(stock, createdAt);
        this.stockCreatedEventDomainEventPublisher = stockCreatedEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        stockCreatedEventDomainEventPublisher.publish(this);
    }
}
