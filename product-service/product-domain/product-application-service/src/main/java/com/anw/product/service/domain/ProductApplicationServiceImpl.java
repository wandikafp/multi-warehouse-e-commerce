package com.anw.product.service.domain;

import com.anw.product.service.domain.dto.ProductBaseResponse;
import com.anw.product.service.domain.dto.create.CreateProductCommand;
import com.anw.product.service.domain.dto.create.CreateProductResponse;
import com.anw.product.service.domain.dto.update.UpdateProductCommand;
import com.anw.product.service.domain.dto.update.UpdateProductResponse;
import com.anw.product.service.domain.ports.input.service.ProductApplicationService;
import com.anw.product.service.domain.ports.output.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductApplicationServiceImpl implements ProductApplicationService {
    private final ProductCommandHandler productCommandHandler;
    ProductApplicationServiceImpl(ProductCommandHandler productCommandHandler) {
        this.productCommandHandler = productCommandHandler;
    }

    @Override
    public List<ProductBaseResponse> getProducts(int page, int size) {
        return productCommandHandler.getProducts(page, size);
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
    public List<ProductBaseResponse> searchProducts(String query) {
        return productCommandHandler.searchProducts(query);
    }

    @Override
    public ProductBaseResponse getProductDetail(String productId) {
        return productCommandHandler.getProductDetail(productId);
    }
}
