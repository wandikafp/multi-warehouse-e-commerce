package com.anw.product.service.dataaccess.product.mapper;

import com.anw.domain.valueobject.Money;
import com.anw.domain.valueobject.ProductId;
import com.anw.product.service.dataaccess.product.entity.CategoryEntity;
import com.anw.product.service.dataaccess.product.entity.ProductEntity;
import com.anw.product.service.domain.entity.Category;
import com.anw.product.service.domain.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductDataAccessMapper {
    public ProductEntity productToProductEntity(Product product) {
        return ProductEntity.builder()
                .id(product.getId().getValue())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice().getAmount())
                .stockQuantity(product.getStockQuantity())
                .imageUrl(product.getImageUrl())
                .category(new CategoryEntity(
                        product.getCategory().getId(),
                        product.getCategory().getName(),
                        product.getCategory().getDescription(),
                        product.getCategory().getIcon()))
                .build();
    }

    public Product productEntityToProduct(ProductEntity entity) {
        return Product.builder()
                .productId(new ProductId(entity.getId()))
                .name(entity.getName())
                .description(entity.getDescription())
                .price(new Money(entity.getPrice()))
                .stockQuantity(entity.getStockQuantity())
                .imageUrl(entity.getImageUrl())
                .category(Category.builder()
                        .categoryId(entity.getCategory().getId())
                        .name(entity.getCategory().getName())
                        .description(entity.getCategory().getDescription())
                        .icon(entity.getCategory().getIcon())
                        .build())
                .build();
    }
}
