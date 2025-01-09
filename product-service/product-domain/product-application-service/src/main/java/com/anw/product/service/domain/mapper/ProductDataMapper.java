package com.anw.product.service.domain.mapper;

import com.anw.domain.valueobject.Money;
import com.anw.domain.valueobject.ProductId;
import com.anw.product.service.domain.dto.CategoryResponse;
import com.anw.product.service.domain.dto.ProductBaseResponse;
import com.anw.product.service.domain.dto.create.CreateProductCommand;
import com.anw.product.service.domain.dto.create.CreateProductResponse;
import com.anw.product.service.domain.dto.update.UpdateProductCommand;
import com.anw.product.service.domain.dto.update.UpdateProductResponse;
import com.anw.product.service.domain.entity.Category;
import com.anw.product.service.domain.entity.Product;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductDataMapper {
    public ProductBaseResponse productToProductBaseResponse(Product product) {
        return ProductBaseResponse.builder()
                .id(product.getId().getValue())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice().getAmount())
                .stockQuantity(product.getStockQuantity())
                .imageUrl(product.getImageUrl())
                .category(CategoryResponse.builder()
                        .id(product.getCategory().getId())
                        .name(product.getCategory().getName())
                        .build())
                .build();
    }

    public Product createProductCommandToProduct(CreateProductCommand createProductCommand) {
        return Product.builder()
                .productId(new ProductId(UUID.randomUUID()))
                .name(createProductCommand.getName())
                .description(createProductCommand.getDescription())
                .price(new Money(createProductCommand.getPrice()))
                .stockQuantity(createProductCommand.getStockQuantity())
                .imageUrl(createProductCommand.getImageUrl())
                .category(Category.builder()
                        .categoryId(createProductCommand.getCategory().getId())
                        .build())
                .build();
    }

    public CreateProductResponse productToCreateProductResponse(Product product) {
        return CreateProductResponse.builder()
                .id(product.getId().getValue())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice().getAmount())
                .stockQuantity(product.getStockQuantity())
                .imageUrl(product.getImageUrl())
                .category(CategoryResponse.builder()
                        .id(product.getCategory().getId())
                        .name(product.getCategory().getName())
                        .build())
                .build();
    }

    public Product updateProductCommandToProduct(UpdateProductCommand updateProductCommand) {
        return Product.builder()
                .productId(new ProductId(updateProductCommand.getId()))
                .name(updateProductCommand.getName())
                .description(updateProductCommand.getDescription())
                .price(new Money(updateProductCommand.getPrice()))
                .stockQuantity(updateProductCommand.getStockQuantity())
                .imageUrl(updateProductCommand.getImageUrl())
                .category(Category.builder()
                        .categoryId(updateProductCommand.getCategory().getId())
                        .build())
                .build();
    }

    public UpdateProductResponse productToUpdateProductResponse(Product product) {
        return UpdateProductResponse.builder()
                .id(product.getId().getValue())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice().getAmount())
                .stockQuantity(product.getStockQuantity())
                .imageUrl(product.getImageUrl())
                .category(CategoryResponse.builder()
                        .id(product.getCategory().getId())
                        .name(product.getCategory().getName())
                        .build())
                .build();
    }
}
