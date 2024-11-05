package com.anw.warehouse.service.domain;

import com.anw.domain.event.publisher.DomainEventPublisher;
import com.anw.warehouse.service.domain.entity.Warehouse;
import com.anw.warehouse.service.domain.event.WarehouseCreatedEvent;
import com.anw.warehouse.service.domain.event.WarehouseUpdatedEvent;

public interface WarehouseDomainService {
    WarehouseCreatedEvent validateAndInitiateWarehouse(
            Warehouse warehouse,
            DomainEventPublisher<WarehouseCreatedEvent> warehouseCreatedEventDomainEventPublisher);
    WarehouseUpdatedEvent validateAndUpdateWarehouse(Warehouse warehouse);
}
