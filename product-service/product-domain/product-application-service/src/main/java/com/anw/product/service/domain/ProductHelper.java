package com.anw.product.service.domain;

import com.anw.product.service.domain.entity.Product;
import com.anw.product.service.domain.event.ProductCreatedEvent;
import com.anw.product.service.domain.event.ProductUpdatedEvent;
import com.anw.product.service.domain.ports.output.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class ProductHelper {
    private final ProductDomainService productDomainService;
    private final ProductRepository productRepository;

    public ProductHelper(ProductDomainService productDomainService,
                           ProductRepository productRepository) {
        this.productDomainService = productDomainService;
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductCreatedEvent persistCreateProduct(Product product) {
        ProductCreatedEvent productCreatedEvent = productDomainService.validateAndInitiateProduct(product, null);
        saveProduct(product);
        log.info("initialize product with id: {}", product.getId().getValue());
        return productCreatedEvent;
    }

    @Transactional
    public ProductUpdatedEvent persistUpdateProduct(Product product) {
        ProductUpdatedEvent productUpdatedEvent = productDomainService.validateAndUpdateProduct(product);
        saveProduct(product);
        log.info("initialize product with id: {}", product.getId().getValue());
        return productUpdatedEvent;
    }

    private void saveProduct(Product product) {
        Product productResult = productRepository.save(product);
        log.info("product is saved with id: {}", productResult.getId().getValue());
    }
}
