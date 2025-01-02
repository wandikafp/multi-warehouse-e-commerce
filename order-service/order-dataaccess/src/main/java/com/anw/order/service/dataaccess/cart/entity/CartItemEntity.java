package com.anw.order.service.dataaccess.cart.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart_item")
public class CartItemEntity {

    @Id
    private UUID cartItemId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CartEntity cart;

    private UUID productId;

    private String name;
    private BigDecimal price;

    private int stock;

    private String imgUrl;

    private int quantity;

    private BigDecimal subTotal;
}
