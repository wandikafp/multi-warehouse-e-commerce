package com.anw.product.service.domain;

import com.anw.domain.dto.PagedRequest;
import com.anw.domain.dto.PagedResponse;
import com.anw.product.service.domain.dto.ProductBaseResponse;
import com.anw.product.service.domain.dto.create.CreateProductCommand;
import com.anw.product.service.domain.dto.create.CreateProductResponse;
import com.anw.product.service.domain.dto.update.UpdateProductCommand;
import com.anw.product.service.domain.dto.update.UpdateProductResponse;
import com.anw.product.service.domain.ports.input.service.ProductApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class ProductApplicationServiceImpl implements ProductApplicationService {
    private final ProductCommandHandler productCommandHandler;
    ProductApplicationServiceImpl(ProductCommandHandler productCommandHandler) {
        this.productCommandHandler = productCommandHandler;
    }

    @Override
    public PagedResponse<ProductBaseResponse> getProducts(PagedRequest pagedRequest) {
        return productCommandHandler.getProducts(pagedRequest);
    }

    @Override
    public CreateProductResponse createProduct(CreateProductCommand createProductCommand) {
        return productCommandHandler.createProduct(createProductCommand);
    }

    @Override
    public UpdateProductResponse updateProduct(UpdateProductCommand updateProductCommand) {
        return productCommandHandler.updateProduct(updateProductCommand);
    }

    @Override
    public ProductBaseResponse getProductDetail(String productId) {
        return productCommandHandler.getProductDetail(productId);
    }

    @Override
    public void deleteProduct(UUID productId) {
        productCommandHandler.deleteProduct(productId);
    }
}
