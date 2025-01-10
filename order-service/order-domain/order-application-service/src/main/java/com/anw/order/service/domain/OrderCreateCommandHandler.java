package com.anw.order.service.domain;

import com.anw.order.service.domain.dto.OrderBaseResponse;
import com.anw.order.service.domain.dto.create.CreateOrderCommand;
import com.anw.order.service.domain.dto.create.CreateOrderResponse;
import com.anw.order.service.domain.entity.Order;
import com.anw.order.service.domain.event.OrderCreatedEvent;
import com.anw.order.service.domain.mapper.OrderDataMapper;
import com.anw.order.service.domain.ports.output.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCreateCommandHandler {
    private final OrderDataMapper orderDataMapper;
    private final OrderCreateHelper orderHelper;
    private final OrderRepository orderRepository;

    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent = orderHelper.persistCreateOrder(order);
        log.info("order is created with id: {}", orderCreatedEvent.getOrder().getId().getValue());
        return orderDataMapper.orderToCreateOrderResponse(orderCreatedEvent.getOrder());
    }

    public List<OrderBaseResponse> getOrders(int page, int size) {
        return orderRepository.findAll(page, size)
                .stream()
                .map(orderDataMapper::orderToOrderBaseResponse)
                .collect(Collectors.toList());
    }

    public OrderBaseResponse getOrderDetail(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return orderDataMapper.orderToOrderBaseResponse(order);
    }

}
