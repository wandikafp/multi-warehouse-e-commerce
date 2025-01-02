package com.anw.order.service.domain.mapper;

import com.anw.domain.valueobject.OrderId;
import com.anw.order.service.domain.dto.OrderBaseResponse;
import com.anw.order.service.domain.dto.create.CreateOrderCommand;
import com.anw.order.service.domain.dto.create.CreateOrderResponse;
import com.anw.order.service.domain.dto.update.UpdateOrderCommand;
import com.anw.order.service.domain.dto.update.UpdateOrderResponse;
import com.anw.order.service.domain.entity.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderDataMapper {
    public OrderBaseResponse orderToOrderBaseResponse(Order order) {
        return OrderBaseResponse.builder()
                .id(order.getId().getValue())
                .build();
    }

    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
        return Order.builder()
                .name(createOrderCommand.getName())
                .description(createOrderCommand.getDescription())
                .price(createOrderCommand.getPrice())
                .stockQuantity(createOrderCommand.getStockQuantity())
                .imageUrl(createOrderCommand.getImageUrl())
                .build();
    }

    public CreateOrderResponse orderToCreateOrderResponse(Order order) {
        return CreateOrderResponse.builder()
                .id(order.getId().getValue())
                .build();
    }

    public Order updateOrderCommandToOrder(UpdateOrderCommand updateOrderCommand) {
        return Order.builder()
                .name(updateOrderCommand.getName())
                .description(updateOrderCommand.getDescription())
                .price(updateOrderCommand.getPrice())
                .stockQuantity(updateOrderCommand.getStockQuantity())
                .imageUrl(updateOrderCommand.getImageUrl())
                .build();
    }

    public UpdateOrderResponse orderToUpdateOrderResponse(Order order) {
        return UpdateOrderResponse.builder()
                .id(order.getId().getValue())
                .build();
    }
}
