package com.anw.order.service.domain.entity;

import com.anw.domain.entity.BaseEntity;
import com.anw.domain.valueobject.Money;
import com.anw.domain.valueobject.ProductId;

public class Product extends BaseEntity<ProductId> {
    private final String name;
    private final Money price;
    private final int stock;
    private final String imgUrl;

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    private Product(Builder builder) {
        super.setId(builder.productId);
        this.name = builder.name;
        this.price = builder.price;
        this.stock = builder.stock;
        this.imgUrl = builder.imgUrl;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ProductId productId;
        private String name;
        private Money price;
        private int stock;
        private String imgUrl;

        public Builder productId(ProductId productId) {
            this.productId = productId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder price(Money price) {
            this.price = price;
            return this;
        }

        public Builder stock(int stock) {
            this.stock = stock;
            return this;
        }

        public Builder imgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
