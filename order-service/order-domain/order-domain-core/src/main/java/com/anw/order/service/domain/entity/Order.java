package com.anw.order.service.domain.entity;

import com.anw.domain.entity.AggregateRoot;
import com.anw.domain.valueobject.OrderId;
import com.anw.domain.valueobject.WarehouseId;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import static com.anw.domain.DomainConstants.UTC;

public class Order extends AggregateRoot<OrderId> {
    private final String name;
    private final String description;
    private final double price;
    private final int stockQuantity;
    private final String imageUrl;

    private Order(Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.price = builder.price;
        this.stockQuantity = builder.stockQuantity;
        this.imageUrl = builder.imageUrl;
    }

    public void initializeOrder() {
        setId(new OrderId(UUID.randomUUID()));
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private String description;
        private double price;
        private int stockQuantity;
        private String imageUrl;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Builder stockQuantity(int stockQuantity) {
            this.stockQuantity = stockQuantity;
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
