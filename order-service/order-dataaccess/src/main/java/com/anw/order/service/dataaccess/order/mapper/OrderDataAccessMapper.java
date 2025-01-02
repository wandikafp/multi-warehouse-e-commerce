package com.anw.order.service.dataaccess.order.mapper;

import com.anw.domain.valueobject.OrderId;
import com.anw.order.service.dataaccess.order.entity.OrderEntity;
import com.anw.order.service.domain.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderDataAccessMapper {
    public OrderEntity orderToOrderEntity(Order order) {
        return OrderEntity.builder()
                .id(order.getId().getValue())
                .build();
    }

    public Order orderEntityToOrder(OrderEntity entity) {
        return Order.builder()
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .stockQuantity(entity.getStockQuantity())
                .imageUrl(entity.getImageUrl())
                .build();
    }
}
