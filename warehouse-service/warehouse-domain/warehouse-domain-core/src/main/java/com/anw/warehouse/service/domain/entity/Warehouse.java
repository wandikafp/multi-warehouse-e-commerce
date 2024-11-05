package com.anw.warehouse.service.domain.entity;

import com.anw.domain.entity.AggregateRoot;
import com.anw.domain.valueobject.Address;
import com.anw.domain.valueobject.UserId;
import com.anw.domain.valueobject.WarehouseId;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import static com.anw.domain.DomainConstants.UTC;

@Getter
public class Warehouse extends AggregateRoot<WarehouseId> {
    private final String name;
    private final UserId adminId;
    private final Address locationAddress;
    @Setter
    private ZonedDateTime createdAt;
    @Setter
    private ZonedDateTime updatedAt;
    @Builder
    public Warehouse(WarehouseId warehouseId, String name, UserId adminId, Address locationAddress, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        super.setId(warehouseId);
        this.name = name;
        this.adminId = adminId;
        this.locationAddress = locationAddress;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void initializeWarehouse() {
        setId(new WarehouseId(UUID.randomUUID()));
        this.createdAt = ZonedDateTime.now(ZoneId.of(UTC));
    }

}
