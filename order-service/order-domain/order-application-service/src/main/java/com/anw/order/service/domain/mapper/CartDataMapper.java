package com.anw.order.service.domain.mapper;

import com.anw.domain.valueobject.CartItemId;
import com.anw.domain.valueobject.Money;
import com.anw.domain.valueobject.ProductId;
import com.anw.domain.valueobject.UserId;
import com.anw.order.service.domain.dto.CartItemCommand;
import com.anw.order.service.domain.dto.CartItemResponse;
import com.anw.order.service.domain.dto.CartResponse;
import com.anw.order.service.domain.entity.Cart;
import com.anw.order.service.domain.entity.CartItem;
import com.anw.order.service.domain.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CartDataMapper {
    public CartResponse cartToCartResponse(Cart cart) {
        List<CartItemResponse> cartItemResponses = cart.getItems().stream()
                .map(this::cartItemToCartItemResponse)
                .collect(Collectors.toList());

        return CartResponse.builder()
                .customerId(cart.getId().getValue())
                .cartItems(cartItemResponses)
                .totalPrice(cart.getPrice().getAmount().toString())
                .build();
    }

    public CartItemResponse cartItemToCartItemResponse(CartItem cartItem) {
        return CartItemResponse.builder()
                .id(cartItem.getId().getValue())
                .productId(cartItem.getProduct().getId().getValue())
                .name(cartItem.getProduct().getName())
                .price(cartItem.getProduct().getPrice().getAmount())
                .stock(cartItem.getProduct().getStock())
                .imgUrl(cartItem.getProduct().getImgUrl())
                .quantity(cartItem.getQuantity())
                .subTotal(cartItem.getSubTotal().getAmount())
                .build();
    }

    public CartItem cartItemCommandToCartItem(UUID customerId, CartItemCommand command) {
        return CartItem.builder()
                .cartItemId(Optional.ofNullable(command.getId())
                        .map(CartItemId::new)
                        .orElse(null))
                .product(Product.builder()
                        .productId(new ProductId(command.getProductId()))
                        .name(command.getName())
                        .price(new Money(command.getPrice()))
                        .stock(command.getStock())
                        .imgUrl(command.getImgUrl())
                        .build())
                .quantity(command.getQuantity())
                .subTotal(new Money(command.getSubTotal()))
                .userId(new UserId(customerId))
                .build();
    }
}
