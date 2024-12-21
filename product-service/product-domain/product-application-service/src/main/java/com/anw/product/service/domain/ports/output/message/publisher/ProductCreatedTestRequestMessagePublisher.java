package com.anw.product.service.domain.ports.output.message.publisher;

import com.anw.domain.event.publisher.DomainEventPublisher;
import com.anw.product.service.domain.event.ProductCreatedEvent;

public interface ProductCreatedTestRequestMessagePublisher extends DomainEventPublisher<ProductCreatedEvent> {
}
