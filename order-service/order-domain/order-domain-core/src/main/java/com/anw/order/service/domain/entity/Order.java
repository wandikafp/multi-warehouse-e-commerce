package com.anw.order.service.domain.entity;

import com.anw.domain.entity.AggregateRoot;
import com.anw.domain.valueobject.*;

import java.util.List;
import java.util.UUID;

public class Order extends AggregateRoot<OrderId> {
    private final Address destinationAddress;
    private final Address sourceAddress;
    private final List<OrderItem> orderItems;
    private final OrderStatus orderStatus;
    private final Money shippingPrice;
    private final Money subTotalPrice;
    private final Money totalPrice;
    private final TrackingId trackingId;

    public Address getDestinationAddress() {
        return destinationAddress;
    }

    public Address getSourceAddress() {
        return sourceAddress;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Money getShippingPrice() {
        return shippingPrice;
    }

    public Money getSubTotalPrice() {
        return subTotalPrice;
    }

    public Money getTotalPrice() {
        return totalPrice;
    }

    public TrackingId getTrackingId() {
        return trackingId;
    }

    private Order(Builder builder) {
        super.setId(builder.orderId);
        this.destinationAddress = builder.destinationAddress;
        this.sourceAddress = builder.sourceAddress;
        this.orderItems = builder.orderItems;
        this.orderStatus = builder.orderStatus;
        this.shippingPrice = builder.shippingPrice;
        this.subTotalPrice = builder.subTotalPrice;
        this.totalPrice = builder.totalPrice;
        this.trackingId = builder.trackingId;
    }

    public void initializeOrder() {
        setId(new OrderId(UUID.randomUUID()));
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private OrderId orderId;
        private Address destinationAddress;
        private Address sourceAddress;
        private List<OrderItem> orderItems;
        private OrderStatus orderStatus;
        private Money shippingPrice;
        private Money subTotalPrice;
        private Money totalPrice;
        private TrackingId trackingId;

        public Builder orderId(OrderId orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder destinationAddress(Address destinationAddress) {
            this.destinationAddress = destinationAddress;
            return this;
        }

        public Builder sourceAddress(Address sourceAddress) {
            this.sourceAddress = sourceAddress;
            return this;
        }

        public Builder orderItems(List<OrderItem> orderItems) {
            this.orderItems = orderItems;
            return this;
        }

        public Builder orderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public Builder shippingPrice(Money shippingPrice) {
            this.shippingPrice = shippingPrice;
            return this;
        }

        public Builder subTotalPrice(Money subTotalPrice) {
            this.subTotalPrice = subTotalPrice;
            return this;
        }

        public Builder totalPrice(Money totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder trackingId(TrackingId trackingId) {
            this.trackingId = trackingId;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
