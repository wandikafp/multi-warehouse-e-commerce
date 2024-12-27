package com.anw.order.service.domain.entity;

import com.anw.domain.entity.AggregateRoot;
import com.anw.domain.valueobject.OrderId;
import com.anw.domain.valueobject.WarehouseId;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import static com.anw.domain.DomainConstants.UTC;

@Getter
public class Order extends AggregateRoot<OrderId> {
    private final String name;
    private final String description;
    private final double price;
    private final int stockQuantity;
    private final String imageUrl;
    private final Category category;


    @Builder
    public Order(OrderId orderId, String name, String description, double price, int stockQuantity, String imageUrl, Category category) {
        super.setId(orderId);
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public void initializeOrder() {
        setId(new OrderId(UUID.randomUUID()));
    }
}
