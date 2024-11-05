package com.anw.warehouse.service.dataaccess.warehouse.mapper;

import com.anw.domain.valueobject.UserId;
import com.anw.domain.valueobject.WarehouseId;
import com.anw.warehouse.service.dataaccess.warehouse.entity.WarehouseAddressEntity;
import com.anw.warehouse.service.dataaccess.warehouse.entity.WarehouseEntity;
import com.anw.warehouse.service.domain.entity.Warehouse;
import com.anw.domain.valueobject.Address;
import org.springframework.stereotype.Component;

@Component
public class WarehouseDataAccessMapper {
    public WarehouseEntity warehouseToWarehouseEntity(Warehouse warehouse) {
        WarehouseAddressEntity addressEntity = addressToAddressEntity(warehouse.getLocationAddress());
        WarehouseEntity warehouseEntity = WarehouseEntity.builder()
                .id(warehouse.getId().getValue())
                .adminId(warehouse.getAdminId().getValue())
                .address(addressEntity)
                .createAt(warehouse.getCreatedAt())
                .updateAt(warehouse.getUpdatedAt())
                .build();
        addressEntity.setWarehouse(warehouseEntity);
        return warehouseEntity;
    }

    public Warehouse warehouseEntityToWarehouse(WarehouseEntity entity) {
        return Warehouse.builder()
                .warehouseId(new WarehouseId(entity.getId()))
                .adminId(new UserId(entity.getAdminId()))
                .locationAddress(AddressEntityToAddress(entity.getAddress()))
                .createdAt(entity.getCreateAt())
                .updatedAt(entity.getUpdateAt())
                .build();
    }

    private Address AddressEntityToAddress(WarehouseAddressEntity addressEntity) {
        return new Address(addressEntity.getId(),
                addressEntity.getStreet(),
                addressEntity.getCity(),
                addressEntity.getProvince(),
                addressEntity.getPostalCode());
    }
    private WarehouseAddressEntity addressToAddressEntity(Address address) {
        return WarehouseAddressEntity.builder()
                .id(address.getId())
                .street(address.getStreet())
                .city(address.getCity())
                .province(address.getProvince())
                .postalCode(address.getPostalCode())
                .build();
    }
}
