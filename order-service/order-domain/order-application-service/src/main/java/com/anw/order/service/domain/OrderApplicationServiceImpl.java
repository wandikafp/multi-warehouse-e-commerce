package com.anw.order.service.domain;

import com.anw.order.service.domain.dto.OrderBaseResponse;
import com.anw.order.service.domain.dto.create.CreateOrderCommand;
import com.anw.order.service.domain.dto.create.CreateOrderResponse;
import com.anw.order.service.domain.ports.input.service.OrderApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderApplicationServiceImpl implements OrderApplicationService {
    private final OrderCreateCommandHandler orderCommandHandler;

    @Override
    public List<OrderBaseResponse> getOrders(int page, int size) {
        return orderCommandHandler.getOrders(page, size);
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        return orderCommandHandler.createOrder(createOrderCommand);
    }

    @Override
    public OrderBaseResponse getOrderDetail(String orderId) {
        return orderCommandHandler.getOrderDetail(orderId);
    }
}
