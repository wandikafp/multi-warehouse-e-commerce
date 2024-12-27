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
        return null;
    }

    @Override
    public CartResponse addItemToCart(UUID cartId, CartItemCommand cartItem) {
        return null;
    }

    @Override
    public CartResponse updateCartItem(UUID cartId, CartItemCommand cartItem) {
        return null;
    }

    @Override
    public CartResponse removeItemFromCart(UUID cartId, CartItemCommand cartItem) {
        return null;
    }

    @Override
    public CartResponse checkoutCart(UUID cartId) {
        return null;
    }

    @Override
    public CartResponse cancelCart(UUID cartId) {
        return null;
    }
}
