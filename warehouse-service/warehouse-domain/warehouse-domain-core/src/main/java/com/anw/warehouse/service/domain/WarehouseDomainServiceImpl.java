package com.anw.warehouse.service.domain;

import com.anw.domain.event.publisher.DomainEventPublisher;
import com.anw.warehouse.service.domain.entity.Warehouse;
import com.anw.warehouse.service.domain.event.WarehouseCreatedEvent;
import com.anw.warehouse.service.domain.event.WarehouseUpdatedEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.anw.domain.DomainConstants.UTC;

@Slf4j
public class WarehouseDomainServiceImpl implements WarehouseDomainService {
    @Override
    public WarehouseCreatedEvent validateAndInitiateWarehouse(Warehouse warehouse, DomainEventPublisher<WarehouseCreatedEvent> warehouseCreatedEventDomainEventPublisher) {
        //TODO validate the user is superAdmin before creating warehouse
        warehouse.initializeWarehouse();
        log.info("Warehouse with id: {} is initiated", warehouse.getId().getValue().toString());
        return new WarehouseCreatedEvent(warehouse, ZonedDateTime.now(ZoneId.of(UTC)), warehouseCreatedEventDomainEventPublisher);
    }
    @Override
    public WarehouseUpdatedEvent validateAndUpdateWarehouse(Warehouse warehouse) {
        //TODO validate the user is superAdmin before updating warehouse
        return new WarehouseUpdatedEvent(warehouse, ZonedDateTime.now(ZoneId.of(UTC)));
    }
}
