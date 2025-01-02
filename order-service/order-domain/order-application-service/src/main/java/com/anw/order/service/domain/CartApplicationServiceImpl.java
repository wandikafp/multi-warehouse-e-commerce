package com.anw.order.service.domain;

import com.anw.order.service.domain.dto.CartItemCommand;
import com.anw.order.service.domain.dto.CartResponse;
import com.anw.order.service.domain.ports.input.service.CartApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartApplicationServiceImpl implements CartApplicationService {

    private final CartCommandHandler cartCommandHandler;

    @Override
    public CartResponse getCartByCustomerId(UUID customerId) {
        return cartCommandHandler.getCartByCustomerId(customerId);
    }

    @Override
    public CartResponse addItemToCart(UUID customerId, CartItemCommand cartItem) {
        return cartCommandHandler.addOrUpdateItemToCart(customerId, cartItem);
    }

    @Override
    public CartResponse updateCartItem(UUID customerId, CartItemCommand cartItem) {
        return cartCommandHandler.addOrUpdateItemToCart(customerId, cartItem);
    }

    @Override
    public CartResponse removeItemFromCart(UUID customerId, CartItemCommand cartItem) {
        return cartCommandHandler.removeItemFromCart(customerId, cartItem);
    }

    @Override
    public CartResponse checkoutCart(UUID cartId) {
        return null;
    }
}
