package com.anw.order.service.dataaccess.order.mapper;

import com.anw.domain.valueobject.*;
import com.anw.order.service.dataaccess.order.entity.OrderEntity;
import com.anw.order.service.dataaccess.order.entity.OrderItemEntity;
import com.anw.order.service.domain.entity.Order;
import com.anw.order.service.domain.entity.OrderItem;
import com.anw.order.service.domain.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderDataAccessMapper {
    public OrderEntity orderToOrderEntity(Order order) {
        return OrderEntity.builder()
                .id(order.getId().getValue())
                .destinationStreet(order.getDestinationAddress().getStreet())
                .destinationCity(order.getDestinationAddress().getCity())
                .destinationProvince(order.getDestinationAddress().getProvince())
                .destinationPostalCode(order.getDestinationAddress().getPostalCode())
                .destinationLongitude(order.getDestinationAddress().getLongitude())
                .destinationLatitude(order.getDestinationAddress().getLatitude())
                .sourceStreet(order.getSourceAddress().getStreet())
                .sourceCity(order.getSourceAddress().getCity())
                .sourceProvince(order.getSourceAddress().getProvince())
                .sourcePostalCode(order.getSourceAddress().getPostalCode())
                .sourceLongitude(order.getSourceAddress().getLongitude())
                .sourceLatitude(order.getSourceAddress().getLatitude())
                .status(order.getOrderStatus())
                .shippingPrice(order.getShippingPrice().getAmount())
                .subTotalPrice(order.getSubTotalPrice().getAmount())
                .trackingId(order.getTrackingId().getValue())
                .items(order.getOrderItems().stream()
                        .map(this::orderItemToOrderItemEntity)
                        .collect(Collectors.toList()))
                .build();
    }

    public Order orderEntityToOrder(OrderEntity entity) {
        return Order.builder()
                .orderId(new OrderId(entity.getId()))
                .destinationAddress(Address.builder()
                        .street(entity.getDestinationStreet())
                        .city(entity.getDestinationCity())
                        .province(entity.getDestinationProvince())
                        .postalCode(entity.getDestinationPostalCode())
                        .longitude(entity.getDestinationLongitude())
                        .latitude(entity.getDestinationLatitude())
                        .build())
                .sourceAddress(Address.builder()
                        .street(entity.getSourceStreet())
                        .city(entity.getSourceCity())
                        .province(entity.getSourceProvince())
                        .postalCode(entity.getSourcePostalCode())
                        .longitude(entity.getSourceLongitude())
                        .latitude(entity.getSourceLatitude())
                        .build())
                .orderStatus(entity.getStatus())
                .shippingPrice(new Money(entity.getShippingPrice()))
                .subTotalPrice(new Money(entity.getSubTotalPrice()))
                .trackingId(new TrackingId(entity.getTrackingId()))
                .orderItems(entity.getItems().stream()
                        .map(this::orderItemEntityToOrderItem)
                        .collect(Collectors.toList()))
                .build();
    }

    private OrderItemEntity orderItemToOrderItemEntity(OrderItem orderItem) {
        return OrderItemEntity.builder()
                .id(orderItem.getId().getValue())
                .productId(orderItem.getProduct().getId().getValue())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getProduct().getPrice().getAmount())
                .subTotal(orderItem.getSubTotal().getAmount())
                .build();
    }

    private OrderItem orderItemEntityToOrderItem(OrderItemEntity entity) {
        return OrderItem.builder()
                .orderItemId(new OrderItemId(entity.getId()))
                .product(Product.builder()
                        .productId(new ProductId(entity.getId()))
                        .price(new Money(entity.getPrice()))
                        .build())
                .quantity(entity.getQuantity())
                .subTotal(new Money(entity.getSubTotal()))
                .build();
    }
}
