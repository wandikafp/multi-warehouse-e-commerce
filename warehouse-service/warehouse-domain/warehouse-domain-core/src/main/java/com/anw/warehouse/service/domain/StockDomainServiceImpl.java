package com.anw.warehouse.service.domain;

import com.anw.domain.event.publisher.DomainEventPublisher;
import com.anw.warehouse.service.domain.entity.Stock;
import com.anw.warehouse.service.domain.event.StockCreatedEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.anw.domain.DomainConstants.UTC;

@Slf4j
public class StockDomainServiceImpl implements StockDomainService {
    @Override
    public StockCreatedEvent validateAndInitiateStock(Stock stock, DomainEventPublisher<StockCreatedEvent> stockCreatedEventDomainEventPublisher) {
        stock.validateStockAmount(0);
        stock.initializeStock();
        stock.setReserveQuantity(0);
        log.info("Stock with id: {} is initiated", stock.getId().getValue().toString());
        return new StockCreatedEvent(stock, ZonedDateTime.now(ZoneId.of(UTC)), stockCreatedEventDomainEventPublisher);
    }
}
