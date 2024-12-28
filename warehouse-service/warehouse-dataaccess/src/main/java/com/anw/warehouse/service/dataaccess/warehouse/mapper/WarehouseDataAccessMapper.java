package com.anw.warehouse.service.dataaccess.warehouse.mapper;

import com.anw.domain.valueobject.BaseId;
import com.anw.domain.valueobject.UserId;
import com.anw.domain.valueobject.WarehouseId;
import com.anw.warehouse.service.dataaccess.warehouse.entity.WarehouseEntity;
import com.anw.warehouse.service.domain.entity.Warehouse;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WarehouseDataAccessMapper {
    public WarehouseEntity warehouseToWarehouseEntity(Warehouse warehouse) {
        return WarehouseEntity.builder()
                .id(warehouse.getId().getValue())
                .name(warehouse.getName())
                .adminId(Optional.ofNullable(warehouse.getAdminId())
                        .map(BaseId::getValue)
                        .orElse(null))
                .street(warehouse.getStreet())
                .city(warehouse.getCity())
                .province(warehouse.getProvince())
                .postalCode(warehouse.getPostalCode())
                .longitude(warehouse.getLongitude())
                .latitude(warehouse.getLatitude())
                .build();
    }

    public Warehouse warehouseEntityToWarehouse(WarehouseEntity entity) {
        return Warehouse.builder()
                .warehouseId(new WarehouseId(entity.getId()))
                .name(entity.getName())
                .adminId(new UserId(entity.getAdminId()))
                .street(entity.getStreet())
                .city(entity.getCity())
                .province(entity.getProvince())
                .postalCode(entity.getPostalCode())
                .longitude(entity.getLongitude())
                .latitude(entity.getLatitude())
                .build();
    }
}
