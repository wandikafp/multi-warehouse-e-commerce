package com.anw.order.service.dataaccess.order.mapper;

import com.anw.domain.valueobject.OrderId;
import com.anw.order.service.dataaccess.order.entity.OrderEntity;
import com.anw.order.service.domain.entity.Category;
import com.anw.order.service.domain.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderDataAccessMapper {
    public OrderEntity orderToOrderEntity(Order order) {
        return OrderEntity.builder()
                .id(order.getId().getValue())
                .name(order.getName())
                .description(order.getDescription())
                .price(order.getPrice())
                .stockQuantity(order.getStockQuantity())
                .imageUrl(order.getImageUrl())
                .category(new CategoryEntity(
                        order.getCategory().getId(),
                        order.getCategory().getName(),
                        order.getCategory().getDescription(),
                        order.getCategory().getIcon()))
                .build();
    }

    public Order orderEntityToOrder(OrderEntity entity) {
        return Order.builder()
                .orderId(new OrderId(entity.getId()))
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
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
