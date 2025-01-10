package com.anw.order.service.dataaccess.order.entity;

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
@Table(name = "order_item")
public class OrderItemEntity {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    private UUID productId;

    private String name;
    private BigDecimal price;

    private String imgUrl;

    private int quantity;

    private BigDecimal subTotal;
}
