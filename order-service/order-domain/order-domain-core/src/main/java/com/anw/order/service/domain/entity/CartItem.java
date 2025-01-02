package com.anw.order.service.domain.entity;

import com.anw.domain.entity.BaseEntity;
import com.anw.domain.valueobject.CartItemId;
import com.anw.domain.valueobject.Money;
import com.anw.domain.valueobject.UserId;

import java.util.UUID;

public class CartItem extends BaseEntity<CartItemId> {
    private final Product product;
    private int quantity;
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private CartItem(Builder builder) {
        super.setId(builder.cartItemId);
        this.product = builder.product;
        this.quantity = builder.quantity;
        this.subTotal = builder.subTotal;
        this.userId = builder.userId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private CartItemId cartItemId;
        private UserId userId;
        private Product product;
        private int quantity;
        private Money price;
        private Money subTotal;

        public Builder cartItemId(CartItemId cartItemId) {
            this.cartItemId = cartItemId;
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

        public CartItem build() {
            return new CartItem(this);
        }
    }

    public void initializeCartItem() {
        setId(new CartItemId(UUID.randomUUID()));
    }
}
