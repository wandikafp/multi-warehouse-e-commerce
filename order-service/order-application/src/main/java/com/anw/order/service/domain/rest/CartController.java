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
//    private final OrderService orderService;

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
    @PostMapping("/{cartId}/add")
    public ResponseEntity<String> addItemToCart(@PathVariable UUID cartId, @RequestBody CartItemCommand cartItem) {
        //TODO: Validate stock availability before adding item to cart?

        // Add item to cart
        cartApplicationService.addItemToCart(cartId, cartItem);
        return new ResponseEntity<>("Product added to cart", HttpStatus.OK);
    }

    // Update Item in CartResponse
    @PutMapping("/{cartId}/update")
    public ResponseEntity<String> updateCartItem(@PathVariable UUID cartId, @RequestBody CartItemCommand cartItem) {
        // TODO: Validate stock availability before updating item in cart?

        cartApplicationService.updateCartItem(cartId, cartItem);
        return new ResponseEntity<>("CartResponse item updated", HttpStatus.OK);
    }

    // Remove Item from CartResponse
    @DeleteMapping("/{cartId}/remove")
    public ResponseEntity<String> removeItemFromCart(@PathVariable UUID cartId, @RequestBody CartItemCommand cartItemCommand) {
        cartApplicationService.removeItemFromCart(cartId, cartItemCommand);
        return new ResponseEntity<>("Product removed from cart", HttpStatus.OK);
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