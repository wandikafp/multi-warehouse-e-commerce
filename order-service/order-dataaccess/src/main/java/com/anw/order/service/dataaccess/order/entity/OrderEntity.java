package com.anw.order.service.dataaccess.order.entity;

import com.anw.domain.valueobject.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order")
public class OrderEntity {
    @Id
    private UUID id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> items;

    private String destinationStreet;
    private String destinationCity;
    private String destinationProvince;
    private String destinationPostalCode;
    private double destinationLongitude;
    private double destinationLatitude;

    private String sourceStreet;
    private String sourceCity;
    private String sourceProvince;
    private String sourcePostalCode;
    private double sourceLongitude;
    private double sourceLatitude;

    private OrderStatus status;

    private BigDecimal shippingPrice;

    private BigDecimal subTotalPrice;

    private UUID trackingId;

}
