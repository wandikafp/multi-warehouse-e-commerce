package com.anw.order.service.dataaccess.cart.adapter;

import com.anw.order.service.dataaccess.cart.entity.CartEntity;
import com.anw.order.service.dataaccess.cart.entity.CartItemEntity;
import com.anw.order.service.dataaccess.cart.mapper.CartDataAccessMapper;
import com.anw.order.service.dataaccess.cart.repository.CartItemJpaRepository;
import com.anw.order.service.dataaccess.cart.repository.CartJpaRepository;
import com.anw.order.service.domain.entity.Cart;
import com.anw.order.service.domain.entity.CartItem;
import com.anw.order.service.domain.ports.output.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartRepositoryImpl implements CartRepository {
    private final CartJpaRepository cartJpaRepository;
    private final CartItemJpaRepository cartItemJpaRepository;
    private final CartDataAccessMapper cartDataAccessMapper;

    @Override
    public Cart getCartByCustomerId(UUID customerId) {
        Optional<CartEntity> cart = cartJpaRepository.findByCustomerId(customerId);
        log.info("cart is preset: {}", cart.isPresent());
        if(cart.isPresent()) {
            return cartDataAccessMapper.cartEntityToCart(cart.get());
        }
        return cartDataAccessMapper.cartEntityToCart(
                cartJpaRepository.save(CartEntity.builder()
                        .customerId(customerId)
                        .build())
        );
    }

    @Override
    public CartItem findByProductIdAndCustomerId(UUID productId, UUID customerID) {
        Optional<CartItemEntity> cartItem = cartItemJpaRepository.findByProductIdAndCustomerId(productId, customerID);
        return cartItem
                .map(cartDataAccessMapper::cartItemEntityToCartItem)
                .orElse(null);
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return cartDataAccessMapper.cartItemEntityToCartItem(
                cartItemJpaRepository.save(
                cartDataAccessMapper.cartItemToCartItemEntity(cartItem)
        ));
    }

    @Override
    public void deleteById(UUID cartItemId) {
        cartItemJpaRepository.deleteById(cartItemId);
    }
}
