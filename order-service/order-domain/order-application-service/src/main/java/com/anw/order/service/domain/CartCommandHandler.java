package com.anw.order.service.domain;

import com.anw.domain.valueobject.Address;
import com.anw.order.service.domain.dto.CartItemCommand;
import com.anw.order.service.domain.dto.CartResponse;
import com.anw.order.service.domain.dto.CheckoutCommand;
import com.anw.order.service.domain.dto.CheckoutResponse;
import com.anw.order.service.domain.entity.Warehouse;
import com.anw.order.service.domain.exception.OrderDomainException;
import com.anw.order.service.domain.mapper.CartDataMapper;
import com.anw.order.service.domain.ports.input.service.WarehouseApplicationService;
import com.anw.order.service.domain.ports.output.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Component
@RequiredArgsConstructor
public class CartCommandHandler {
    private final CartRepository cartRepository;
    private final CartDataMapper cartDataMapper;
    private final CartHelper cartHelper;
//    private final WarehouseApplicationService warehouseApplicationService;

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

    public CheckoutResponse checkoutChart(CheckoutCommand command) {
        //TODO:
        // check stock for all orderItem (call to warehouse service)
        // get all the orderItem and calculate the price
        // calculate shipping cost
//        Optional<Warehouse> sourceWarehouse = warehouseApplicationService.getNearestWarehouse(command.getDeliveryAddress());
//        if (sourceWarehouse.isEmpty()) {
//            throw new OrderDomainException("Stock does not exist");
//        }
//        Address  sourceAddress = cartDataMapper.warehouseToAddress(sourceWarehouse.get());
        // Money shippingPrice = calculateShippingPrice(Address deliveryAddress, Address sourceAddress)
        CartResponse cart = cartDataMapper.cartToCartResponse(
                cartRepository.getCartByCustomerId(command.getCustomerId()));
        return CheckoutResponse.builder()
                .customerId(command.getCustomerId())
                .cartItems(cart.getCartItems())
                .totalPrice(cart.getTotalPrice())
                .deliveryAddress(command.getDeliveryAddress())
                //.shippingPrice(shippingPrice)
                .build();
    }
}
