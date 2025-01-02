package com.anw.order.service.domain.entity;

import com.anw.domain.entity.BaseEntity;
import com.anw.domain.valueobject.Money;
import com.anw.domain.valueobject.UserId;

import java.util.List;

public class Cart extends BaseEntity<UserId> {
    private final Money price;
    private final List<CartItem> items;

    public Money getPrice() {
        return price;
    }

    public List<CartItem> getItems() {
        return items;
    }

    private Cart(Builder builder) {
        super.setId(builder.userId);
        this.price = builder.price;
        this.items = builder.items;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UserId userId;
        private Money price;
        private List<CartItem> items;

        public Builder userId(UserId userId) {
            this.userId = userId;
            return this;
        }

        public Builder price(Money price) {
            this.price = price;
            return this;
        }

        public Builder items(List<CartItem> items) {
            this.items = items;
            return this;
        }

        public Cart build() {
            return new Cart(this);
        }
    }
}
