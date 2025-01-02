package com.anw.order.service.domain;

import com.anw.order.service.domain.entity.CartItem;
import com.anw.order.service.domain.ports.output.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CartHelper {
    private final CartRepository cartRepository;
    @Transactional
    public void persistSaveCartItem(CartItem cartItem) {
        CartItem existingCartItem = cartRepository.findByProductIdAndCustomerId(
                cartItem.getProduct().getId().getValue(), cartItem.getUserId().getValue());
        if (existingCartItem != null) {
            log.info("cart with productId {} and customerId {} exist with id {}",
                    cartItem.getProduct().getId().getValue(),
                    cartItem.getUserId().getValue(),
                    existingCartItem.getId().getValue());
            cartItem.setId(existingCartItem.getId());
            cartItem.setQuantity(cartItem.getQuantity() + existingCartItem.getQuantity());
        } else if (cartItem.getId() == null) {
            log.info("new cartItem for productId {} and customerId {}",
                    cartItem.getProduct().getId().getValue(),
                    cartItem.getUserId().getValue());
            cartItem.initializeCartItem();
        }
        cartRepository.save(cartItem);
    }

    @Transactional
    public void persistRemoveCartItem(CartItem cartItem) {
        cartRepository.deleteById(cartItem.getId().getValue());
    }
}
