package com.anw.product.service.domain;

import com.anw.domain.event.publisher.DomainEventPublisher;
import com.anw.product.service.domain.entity.Product;
import com.anw.product.service.domain.event.ProductCreatedEvent;
import com.anw.product.service.domain.event.ProductUpdatedEvent;

public interface ProductDomainService {
    ProductCreatedEvent validateAndInitiateProduct(
            Product product,
            DomainEventPublisher<ProductCreatedEvent> productCreatedEventDomainEventPublisher);
    ProductUpdatedEvent validateAndUpdateProduct(Product product);
}
