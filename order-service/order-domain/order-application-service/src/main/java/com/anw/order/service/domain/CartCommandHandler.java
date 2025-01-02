package com.anw.order.service.domain;

import com.anw.order.service.domain.dto.CartItemCommand;
import com.anw.order.service.domain.dto.CartResponse;
import com.anw.order.service.domain.mapper.CartDataMapper;
import com.anw.order.service.domain.ports.output.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CartCommandHandler {
    private final CartRepository cartRepository;
    private final CartDataMapper cartDataMapper;
    private final CartHelper cartHelper;

    public CartResponse getCartByCustomerId(UUID customerId) {
        return cartDataMapper.cartToCartResponse(
                cartRepository.getCartByCustomerId(customerId));
    }

    public CartResponse addOrUpdateItemToCart(UUID customerId, CartItemCommand cartItemCommand) {
        cartHelper.persistSaveCartItem(cartDataMapper.cartItemCommandToCartItem(customerId, cartItemCommand));
        return cartDataMapper.cartToCartResponse(
                cartRepository.getCartByCustomerId(customerId));
    }

    public CartResponse removeItemFromCart(UUID customerId, CartItemCommand cartItemCommand) {
        cartHelper.persistRemoveCartItem(cartDataMapper.cartItemCommandToCartItem(customerId, cartItemCommand));
        return cartDataMapper.cartToCartResponse(
                cartRepository.getCartByCustomerId(customerId));
    }
}
