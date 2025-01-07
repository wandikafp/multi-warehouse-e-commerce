package com.anw.order.service.domain.ports.input.service;

import com.anw.order.service.domain.dto.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public interface CartApplicationService {

    CartResponse getCartByCustomerId(UUID customerId);
    CartResponse addItemToCart(UUID cartId, CartItemCommand cartItem);
    CartResponse updateCartItem(UUID cartId, CartItemCommand cartItem);
    CartResponse removeItemFromCart(UUID cartId, CartItemCommand cartItem);
    CheckoutResponse checkoutCart(CheckoutCommand cartId);
}
