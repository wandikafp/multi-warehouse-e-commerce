package com.anw.order.service.domain.ports.output.message.publisher;

import com.anw.domain.event.publisher.DomainEventPublisher;
import com.anw.order.service.domain.event.OrderCreatedEvent;

public interface OrderCreatedTestRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {
}
