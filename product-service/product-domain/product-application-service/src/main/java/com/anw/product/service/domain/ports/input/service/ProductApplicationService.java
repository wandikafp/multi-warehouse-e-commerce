package com.anw.product.service.domain.ports.input.service;

import com.anw.domain.dto.PagedRequest;
import com.anw.domain.dto.PagedResponse;
import com.anw.product.service.domain.dto.ProductBaseResponse;
import com.anw.product.service.domain.dto.create.CreateProductCommand;
import com.anw.product.service.domain.dto.create.CreateProductResponse;
import com.anw.product.service.domain.dto.update.UpdateProductCommand;
import com.anw.product.service.domain.dto.update.UpdateProductResponse;
import jakarta.validation.Valid;

import java.util.UUID;

public interface ProductApplicationService {
    PagedResponse<ProductBaseResponse> getProducts(PagedRequest pagedRequest);
    CreateProductResponse createProduct(@Valid CreateProductCommand createProductCommand);
    UpdateProductResponse updateProduct(@Valid UpdateProductCommand updateProductCommand);
    ProductBaseResponse getProductDetail(String productId);
    void deleteProduct(UUID productId);
}
