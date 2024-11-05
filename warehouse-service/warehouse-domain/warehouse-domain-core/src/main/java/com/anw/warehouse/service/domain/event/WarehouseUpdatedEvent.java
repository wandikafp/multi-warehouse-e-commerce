package com.anw.warehouse.service.domain.event;

import com.anw.domain.event.publisher.DomainEventPublisher;
import com.anw.warehouse.service.domain.entity.Warehouse;

import java.time.ZonedDateTime;

public class WarehouseUpdatedEvent extends WarehouseEvent {
    public WarehouseUpdatedEvent(Warehouse warehouse,
                                 ZonedDateTime createdAt) {
        super(warehouse, createdAt);
    }

    @Override
    public void fire() {
    }
}
