package com.anw.order.service.domain.rest;

import com.anw.order.service.domain.dto.OrderBaseResponse;
import com.anw.order.service.domain.dto.create.CreateOrderCommand;
import com.anw.order.service.domain.dto.create.CreateOrderResponse;
import com.anw.order.service.domain.dto.update.UpdateOrderCommand;
import com.anw.order.service.domain.dto.update.UpdateOrderResponse;
import com.anw.order.service.domain.ports.input.service.OrderApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/Order", produces = "application/json")
public class OrderController {

    private final OrderApplicationService orderApplicationService;

    public OrderController(OrderApplicationService orderApplicationService) {
        this.orderApplicationService = orderApplicationService;
    }
    @GetMapping
    public ResponseEntity<List<OrderBaseResponse>> getOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        log.info("Retrieving Orders: ");
        List<OrderBaseResponse> Orders = orderApplicationService.getOrders(page, size);
        return ResponseEntity.ok(Orders);
    }

    @GetMapping("/search")
    public ResponseEntity<List<OrderBaseResponse>> searchOrders(@RequestParam String query) {
        log.info("Searching Orders with query: {}", query);
        List<OrderBaseResponse> orders = orderApplicationService.searchOrders(query);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderBaseResponse> getOrderDetail(@PathVariable String orderId) {
        log.info("Retrieving Order Detail for id: {}", orderId);
        OrderBaseResponse orderDetail = orderApplicationService.getOrderDetail(orderId);
        return ResponseEntity.ok(orderDetail);
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderCommand createOrderCommand) {
        log.info("Creating Order: ");
        CreateOrderResponse createOrderResponse = orderApplicationService.createOrder(createOrderCommand);
        log.info("Order created with id: {}", createOrderResponse);
        return ResponseEntity.ok(createOrderResponse);
    }
    @PutMapping
    public ResponseEntity<UpdateOrderResponse> updateOrder(@RequestBody UpdateOrderCommand updateOrderCommand) {
        log.info("Updating Order: ");
        UpdateOrderResponse updateOrderResponse = orderApplicationService.updateOrder(updateOrderCommand);
        log.info("Order updated with id: {}", updateOrderResponse);
        return ResponseEntity.ok(updateOrderResponse);
    }
}
