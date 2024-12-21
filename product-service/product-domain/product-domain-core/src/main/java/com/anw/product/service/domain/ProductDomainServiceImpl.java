package com.anw.product.service.domain;

import com.anw.domain.event.publisher.DomainEventPublisher;
import com.anw.product.service.domain.entity.Product;
import com.anw.product.service.domain.event.ProductCreatedEvent;
import com.anw.product.service.domain.event.ProductUpdatedEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.anw.domain.DomainConstants.UTC;

@Slf4j
public class ProductDomainServiceImpl implements ProductDomainService {
    @Override
    public ProductCreatedEvent validateAndInitiateProduct(Product product, DomainEventPublisher<ProductCreatedEvent> productCreatedEventDomainEventPublisher) {
        product.initializeProduct();
        log.info("product with id: {} is initiated", product.getId().getValue().toString());
        return new ProductCreatedEvent(product, ZonedDateTime.now(ZoneId.of(UTC)), productCreatedEventDomainEventPublisher);
    }
    @Override
    public ProductUpdatedEvent validateAndUpdateProduct(Product product) {
        return new ProductUpdatedEvent(product, ZonedDateTime.now(ZoneId.of(UTC)));
    }
}
