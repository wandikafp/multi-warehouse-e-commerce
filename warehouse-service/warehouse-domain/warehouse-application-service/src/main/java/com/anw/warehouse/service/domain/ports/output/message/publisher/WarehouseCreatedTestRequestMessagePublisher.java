package com.anw.warehouse.service.domain.ports.output.message.publisher;

import com.anw.domain.event.publisher.DomainEventPublisher;
import com.anw.warehouse.service.domain.event.WarehouseCreatedEvent;

public interface WarehouseCreatedTestRequestMessagePublisher extends DomainEventPublisher<WarehouseCreatedEvent> {
}
