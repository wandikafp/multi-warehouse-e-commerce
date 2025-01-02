package com.anw.order.service.domain.ports.output.repository;

import com.anw.order.service.domain.entity.Cart;
import com.anw.order.service.domain.entity.CartItem;

import java.util.UUID;

public interface CartRepository {
    Cart getCartByCustomerId(UUID customerId);
    CartItem findByProductIdAndCustomerId(UUID productId, UUID customerID);
    CartItem save(CartItem cartItem);
    void deleteById(UUID cartItemId);
}
