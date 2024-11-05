package com.anw.order.service.domain.entity;

import com.anw.domain.entity.AggregateRoot;
import com.anw.domain.valueobject.*;

import java.util.List;

public class Order extends AggregateRoot<OrderId> {
    private final UserId customerId;
    private final Address deliveryAddress;
    private final Money price;
    private final List<OrderItem> items;
    private final OrderStatus orderStatus;

    private Order(Builder builder) {
        super.setId(builder.orderId);
        customerId = builder.customerId;
        deliveryAddress = builder.deliveryAddress;
        price = builder.price;
        items = builder.items;
        orderStatus = builder.orderStatus;
    }

    public static Builder builder() {
        return new Builder();
    }

    public UserId getCustomerId() {
        return customerId;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public Money getPrice() {
        return price;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public static final class Builder {
        private OrderId orderId;
        private UserId customerId;
        private Address deliveryAddress;
        private Money price;
        private List<OrderItem> items;
        private OrderStatus orderStatus;

        private Builder() {
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder customerId(UserId val) {
            customerId = val;
            return this;
        }

        public Builder deliveryAddress(Address val) {
            deliveryAddress = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder items(List<OrderItem> val) {
            items = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
