package com.anw.order.service.domain.rest;

import com.anw.order.service.domain.dto.CartItemCommand;
import com.anw.order.service.domain.dto.CartResponse;
import com.anw.order.service.domain.ports.input.service.CartApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartApplicationService cartApplicationService;

    @Autowired
    public CartController(CartApplicationService cartApplicationService) {
        this.cartApplicationService = cartApplicationService;
    }

    // Get CartResponse by Customer ID
    @GetMapping("/{customerId}")
    public ResponseEntity<CartResponse> getCart(@PathVariable UUID customerId) {
        CartResponse cart = cartApplicationService.getCartByCustomerId(customerId);
        if (cart == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    // Add Item to CartResponse
    @PostMapping("/{customerId}/add")
    public ResponseEntity<CartResponse> addItemToCart(@PathVariable UUID customerId, @RequestBody CartItemCommand cartItem) {
        // Add item to cart
        CartResponse cart = cartApplicationService.addItemToCart(customerId, cartItem);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    // Update Item in CartResponse
    @PutMapping("/{customerId}/update")
    public ResponseEntity<CartResponse> updateCartItem(@PathVariable UUID customerId, @RequestBody CartItemCommand cartItem) {

        CartResponse cart = cartApplicationService.updateCartItem(customerId, cartItem);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    // Remove Item from CartResponse
    @DeleteMapping("/{customerId}/remove")
    public ResponseEntity<CartResponse> removeItemFromCart(@PathVariable UUID customerId, @RequestBody CartItemCommand cartItemCommand) {
        CartResponse cart = cartApplicationService.removeItemFromCart(customerId, cartItemCommand);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    // Proceed to Checkout
    @PostMapping("/{cartId}/checkout")
    public ResponseEntity<String> checkout(@PathVariable UUID cartId) {
        // TODO: Validate cart before proceeding to checkout (e.g. cart not empty, all items in stock, price correct, etc.)
        // then proceed to order creation

//        CartResponse cart = cartApplicationService.getCartById(cartId);
//        if (cart == null || cart.getCartItems().isEmpty()) {
//            return new ResponseEntity<>("CartResponse is empty", HttpStatus.BAD_REQUEST);
//        }

        // Proceed to order creation
//        orderService.createOrder(cart);

        // Update cart status
//        cartApplicationService.updateCartStatus(cartId, "CHECKED_OUT");

        return new ResponseEntity<>("Checkout successful, order created", HttpStatus.OK);
    }
}