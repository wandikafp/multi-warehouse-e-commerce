package com.anw.order.service.domain.mapper;

import com.anw.order.service.domain.dto.OrderBaseResponse;
import com.anw.order.service.domain.dto.create.CreateOrderCommand;
import com.anw.order.service.domain.dto.create.CreateOrderResponse;
import com.anw.order.service.domain.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderDataMapper {
    public OrderBaseResponse orderToOrderBaseResponse(Order order) {
        return OrderBaseResponse.builder()
                .id(order.getId().getValue())
                .build();
    }

    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
        return Order.builder()
                .build();
    }

    public CreateOrderResponse orderToCreateOrderResponse(Order order) {
        return CreateOrderResponse.builder()
                .id(order.getId().getValue())
                .build();
    }
}
