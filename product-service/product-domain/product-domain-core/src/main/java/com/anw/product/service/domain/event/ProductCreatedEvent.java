package com.anw.product.service.domain.event;

import com.anw.domain.event.publisher.DomainEventPublisher;
import com.anw.product.service.domain.entity.Product;

import java.time.ZonedDateTime;

public class ProductCreatedEvent extends ProductEvent {
    private final DomainEventPublisher<ProductCreatedEvent> productCreatedEventDomainEventPublisher;

    public ProductCreatedEvent(Product product,
                                 ZonedDateTime createdAt,
                                 DomainEventPublisher<ProductCreatedEvent> productCreatedEventDomainEventPublisher) {
        super(product, createdAt);
        this.productCreatedEventDomainEventPublisher = productCreatedEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        productCreatedEventDomainEventPublisher.publish(this);
    }
}
