package com.anw.order.service.domain.entity;

import com.anw.domain.entity.BaseEntity;
import com.anw.domain.valueobject.CartItemId;
import com.anw.domain.valueobject.Money;
import com.anw.domain.valueobject.OrderItemId;
import com.anw.domain.valueobject.UserId;

public class OrderItem extends BaseEntity<OrderItemId> {
    private final Product product;
    private final int quantity;
    private final Money subTotal;
    private final UserId userId;

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getSubTotal() {
        return subTotal;
    }

    public UserId getUserId() {
        return userId;
    }

    private OrderItem(Builder builder) {
        super.setId(builder.orderItemId);
        this.product = builder.product;
        this.quantity = builder.quantity;
        this.subTotal = builder.subTotal;
        this.userId = builder.userId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private OrderItemId orderItemId;
        private UserId userId;
        private Product product;
        private int quantity;
        private Money price;
        private Money subTotal;

        public Builder orderItemId(OrderItemId orderItemId) {
            this.orderItemId = orderItemId;
            return this;
        }

        public Builder userId(UserId userId) {
            this.userId = userId;
            return this;
        }

        public Builder product(Product product) {
            this.product = product;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder subTotal(Money subTotal) {
            this.subTotal = subTotal;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
