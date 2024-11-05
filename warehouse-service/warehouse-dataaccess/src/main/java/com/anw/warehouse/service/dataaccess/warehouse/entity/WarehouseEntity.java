package com.anw.warehouse.service.dataaccess.warehouse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "warehouse")
public class WarehouseEntity {
    @Id
    private UUID id;
    private UUID adminId;

    @Column(nullable = false)
    private ZonedDateTime createAt;

    private ZonedDateTime updateAt;

    @OneToOne(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private WarehouseAddressEntity address;
}
