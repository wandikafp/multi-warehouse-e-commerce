package com.anw.product.service.domain.event;

import com.anw.domain.event.publisher.DomainEventPublisher;
import com.anw.product.service.domain.entity.Product;

import java.time.ZonedDateTime;

public class ProductUpdatedEvent extends ProductEvent {
    public ProductUpdatedEvent(Product product,
                                 ZonedDateTime createdAt) {
        super(product, createdAt);
    }

    @Override
    public void fire() {
    }
}
