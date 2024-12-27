package com.anw.order.service.domain.ports.input.service;

import com.anw.order.service.domain.dto.CartItemCommand;
import com.anw.order.service.domain.dto.CartItemResponse;
import com.anw.order.service.domain.dto.CartResponse;

import java.util.List;
import java.util.UUID;

public interface CartApplicationService {

    CartResponse getCartByCustomerId(UUID customerId);
    CartResponse addItemToCart(UUID cartId, CartItemCommand cartItem);
    CartResponse updateCartItem(UUID cartId, CartItemCommand cartItem);
    CartResponse removeItemFromCart(UUID cartId, CartItemCommand cartItem);
    CartResponse checkoutCart(UUID cartId);
    CartResponse cancelCart(UUID cartId);
//    CartResponse updateCartStatus(Long cartId, CartStatus status);
}
