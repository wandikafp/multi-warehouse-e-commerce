package com.anw.product.service.domain.ports.input.service;

import com.anw.product.service.domain.dto.ProductBaseResponse;
import com.anw.product.service.domain.dto.create.CreateProductCommand;
import com.anw.product.service.domain.dto.create.CreateProductResponse;
import com.anw.product.service.domain.dto.update.UpdateProductCommand;
import com.anw.product.service.domain.dto.update.UpdateProductResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface ProductApplicationService {
    List<ProductBaseResponse> getProducts(int page, int size);
    CreateProductResponse createProduct(@Valid CreateProductCommand createProductCommand);
    UpdateProductResponse updateProduct(@Valid UpdateProductCommand updateProductCommand);
    List<ProductBaseResponse> searchProducts(String query);
    ProductBaseResponse getProductDetail(String productId);
}
