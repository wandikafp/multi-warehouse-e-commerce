package com.anw.product.service.domain;

import com.anw.domain.dto.PagedRequest;
import com.anw.domain.dto.PagedResponse;
import com.anw.product.service.domain.dto.ProductBaseResponse;
import com.anw.product.service.domain.dto.create.CreateProductCommand;
import com.anw.product.service.domain.dto.create.CreateProductResponse;
import com.anw.product.service.domain.dto.update.UpdateProductCommand;
import com.anw.product.service.domain.dto.update.UpdateProductResponse;
import com.anw.product.service.domain.entity.Product;
import com.anw.product.service.domain.event.ProductCreatedEvent;
import com.anw.product.service.domain.event.ProductUpdatedEvent;
import com.anw.product.service.domain.mapper.ProductDataMapper;
import com.anw.product.service.domain.ports.output.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductCommandHandler {
    private final ProductDataMapper productDataMapper;
    private final ProductHelper productHelper;
    private final ProductRepository productRepository;

    public PagedResponse<ProductBaseResponse> getProducts(PagedRequest pagedRequest) {
        PagedResponse<Product> products = productRepository.findAll(pagedRequest);
        return new PagedResponse<>(products.getPage(), products.getSize(), products.getTotalElements(), products.getTotalPages(),
                products.getData()
                        .stream()
                        .map(productDataMapper::productToProductBaseResponse)
                        .collect(Collectors.toList()));
    }

    public ProductBaseResponse getProductDetail(String productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return productDataMapper.productToProductBaseResponse(product);
    }

    public CreateProductResponse createProduct(CreateProductCommand createProductCommand) {
        Product product = productDataMapper.createProductCommandToProduct(createProductCommand);
        ProductCreatedEvent productCreatedEvent = productHelper.persistCreateProduct(product);
        log.info("product is created with id: {}", productCreatedEvent.getProduct().getId().getValue());
        //Below event just for testing
        return productDataMapper.productToCreateProductResponse(productCreatedEvent.getProduct());
    }

    public UpdateProductResponse updateProduct(UpdateProductCommand updateProductCommand) {
        Product product = productDataMapper.updateProductCommandToProduct(updateProductCommand);
        ProductUpdatedEvent productUpdatedEvent = productHelper.persistUpdateProduct(product);
        return productDataMapper.productToUpdateProductResponse(productUpdatedEvent.getProduct());
    }

    @Transactional
    public void deleteProduct(UUID categoryId) {
        productRepository.deleteById(categoryId);
    }
}
