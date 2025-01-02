package com.anw.order.service.dataaccess.cart.mapper;

import com.anw.domain.valueobject.CartItemId;
import com.anw.domain.valueobject.Money;
import com.anw.domain.valueobject.ProductId;
import com.anw.domain.valueobject.UserId;
import com.anw.order.service.dataaccess.cart.entity.CartEntity;
import com.anw.order.service.dataaccess.cart.entity.CartItemEntity;
import com.anw.order.service.domain.entity.Cart;
import com.anw.order.service.domain.entity.CartItem;
import com.anw.order.service.domain.entity.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CartDataAccessMapper {
    public Cart cartEntityToCart(CartEntity entity) {
        List<CartItem> cartItems = Optional.ofNullable(entity.getItems())
                .map(x -> x.stream()
                        .map(this::cartItemEntityToCartItem)
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>());

        Money totalPrice = cartItems.stream()
                .map(CartItem::getSubTotal)
                .reduce(Money.ZERO, Money::add);

        return Cart.builder()
                .userId(new UserId(entity.getCustomerId()))
                .price(totalPrice)
                .items(cartItems)
                .build();
    }

    public CartItem cartItemEntityToCartItem(CartItemEntity entity) {
        return CartItem.builder()
                .cartItemId(new CartItemId(entity.getCartItemId()))
                .product(Product.builder()
                        .productId(new ProductId(entity.getProductId()))
                        .name(entity.getName())
                        .price(new Money(entity.getPrice()))
                        .stock(entity.getStock())
                        .imgUrl(entity.getImgUrl())
                        .build())
                .quantity(entity.getQuantity())
                .subTotal(new Money(entity.getSubTotal()))
                .userId(new UserId(entity.getCart().getCustomerId()))
                .build();
    }

    public CartItemEntity cartItemToCartItemEntity(CartItem cartItem) {
        return CartItemEntity.builder()
                .cartItemId(cartItem.getId().getValue())
                .productId(cartItem.getProduct().getId().getValue())
                .price(cartItem.getProduct().getPrice().getAmount())
                .name(cartItem.getProduct().getName())
                .stock(cartItem.getProduct().getStock())
                .imgUrl(cartItem.getProduct().getImgUrl())
                .quantity(cartItem.getQuantity())
                .subTotal(cartItem.getSubTotal().getAmount())
                .cart(CartEntity.builder()
                        .customerId(cartItem.getUserId().getValue())
                        .build())
                .build();
    }
}
