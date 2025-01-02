package com.anw.warehouse.service.domain;

import com.anw.domain.event.publisher.DomainEventPublisher;
import com.anw.warehouse.service.domain.entity.Stock;
import com.anw.warehouse.service.domain.event.StockCreatedEvent;
import com.anw.warehouse.service.domain.event.WarehouseCreatedEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.anw.domain.DomainConstants.UTC;

@Slf4j
public class StockDomainServiceImpl implements StockDomainService {
    @Override
    public StockCreatedEvent validateAndInitiateStock(Stock stock, DomainEventPublisher<StockCreatedEvent> stockCreatedEventDomainEventPublisher) {
        stock.initializeStock();
        log.info("Warehouse with id: {} is initiated", stock.getId().getValue().toString());
        return new StockCreatedEvent(stock, ZonedDateTime.now(ZoneId.of(UTC)), stockCreatedEventDomainEventPublisher);
    }
}
