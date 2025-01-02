package com.anw.warehouse.service.domain;

import com.anw.domain.event.publisher.DomainEventPublisher;
import com.anw.warehouse.service.domain.entity.Stock;
import com.anw.warehouse.service.domain.event.StockCreatedEvent;

public interface StockDomainService {
    StockCreatedEvent validateAndInitiateStock(
            Stock stock,
            DomainEventPublisher<StockCreatedEvent> stockCreatedEventDomainEventPublisher);
}
