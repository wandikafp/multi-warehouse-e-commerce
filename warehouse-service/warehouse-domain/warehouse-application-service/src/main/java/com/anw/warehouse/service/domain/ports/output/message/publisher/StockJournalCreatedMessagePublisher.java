package com.anw.warehouse.service.domain.ports.output.message.publisher;

import com.anw.domain.event.publisher.DomainEventPublisher;
import com.anw.warehouse.service.domain.event.StockJournalCreatedEvent;

public interface StockJournalCreatedMessagePublisher extends DomainEventPublisher<StockJournalCreatedEvent> {
}
