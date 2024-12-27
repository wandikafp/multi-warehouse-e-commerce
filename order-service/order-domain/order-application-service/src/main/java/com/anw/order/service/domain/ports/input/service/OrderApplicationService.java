package com.anw.order.service.domain.ports.input.service;

import com.anw.order.service.domain.dto.OrderBaseResponse;
import com.anw.order.service.domain.dto.create.CreateOrderCommand;
import com.anw.order.service.domain.dto.create.CreateOrderResponse;
import com.anw.order.service.domain.dto.update.UpdateOrderCommand;
import com.anw.order.service.domain.dto.update.UpdateOrderResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface OrderApplicationService {
    List<OrderBaseResponse> getOrders(int page, int size);
    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);
    UpdateOrderResponse updateOrder(@Valid UpdateOrderCommand updateOrderCommand);
    List<OrderBaseResponse> searchOrders(String query);
    OrderBaseResponse getOrderDetail(String orderId);
}
