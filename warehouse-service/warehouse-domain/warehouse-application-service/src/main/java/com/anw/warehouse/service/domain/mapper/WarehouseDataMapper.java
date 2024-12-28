package com.anw.warehouse.service.domain.mapper;

import com.anw.domain.valueobject.Role;
import com.anw.domain.valueobject.UserId;
import com.anw.domain.valueobject.WarehouseId;
import com.anw.warehouse.service.domain.dto.WarehouseBaseResponse;
import com.anw.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.anw.warehouse.service.domain.dto.create.CreateWarehouseResponse;
import com.anw.warehouse.service.domain.dto.message.UserResponse;
import com.anw.warehouse.service.domain.dto.update.UpdateWarehouseCommand;
import com.anw.warehouse.service.domain.dto.update.UpdateWarehouseResponse;
import com.anw.warehouse.service.domain.entity.User;
import com.anw.warehouse.service.domain.entity.Warehouse;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class WarehouseDataMapper {
    public WarehouseBaseResponse warehouseToWarehouseBaseResponse(Warehouse warehouse) {
        return WarehouseBaseResponse.builder()
                .id(warehouse.getId().getValue())
                .name(warehouse.getName())
                .adminId(warehouse.getAdminId().getValue())
                .street(warehouse.getStreet())
                .city(warehouse.getCity())
                .province(warehouse.getProvince())
                .postalCode(warehouse.getPostalCode())
                .longitude(warehouse.getLongitude())
                .latitude(warehouse.getLatitude())
                .build();
    }

    public Warehouse createWarehouseCommandToWarehouse(CreateWarehouseCommand createWarehouseCommand) {
        return Warehouse.builder()
                .name(createWarehouseCommand.getName())
                .adminId(new UserId(createWarehouseCommand.getAdminId()))
                .street(createWarehouseCommand.getStreet())
                .city(createWarehouseCommand.getCity())
                .province(createWarehouseCommand.getProvince())
                .postalCode(createWarehouseCommand.getPostalCode())
                .longitude(createWarehouseCommand.getLongitude())
                .latitude(createWarehouseCommand.getLatitude())
                .build();
    }

    public CreateWarehouseResponse warehouseToCreateWarehouseResponse(Warehouse warehouse) {
        return CreateWarehouseResponse.builder()
                .id(warehouse.getId().getValue())
                .name(warehouse.getName())
                .adminId(warehouse.getAdminId().getValue())
                .street(warehouse.getStreet())
                .city(warehouse.getCity())
                .province(warehouse.getProvince())
                .postalCode(warehouse.getPostalCode())
                .longitude(warehouse.getLongitude())
                .latitude(warehouse.getLatitude())
                .build();
    }

    public Warehouse updateWarehouseCommandToWarehouse(UpdateWarehouseCommand updateWarehouseCommand) {
        return Warehouse.builder()
                .warehouseId(new WarehouseId(updateWarehouseCommand.getId()))
                .name(updateWarehouseCommand.getName())
                .adminId(new UserId(updateWarehouseCommand.getAdminId()))
                .street(updateWarehouseCommand.getStreet())
                .city(updateWarehouseCommand.getCity())
                .province(updateWarehouseCommand.getProvince())
                .postalCode(updateWarehouseCommand.getPostalCode())
                .longitude(updateWarehouseCommand.getLongitude())
                .latitude(updateWarehouseCommand.getLatitude())
                .build();
    }

    public UpdateWarehouseResponse warehouseToUpdateWarehouseResponse(Warehouse warehouse) {
        return UpdateWarehouseResponse.builder()
                .id(warehouse.getId().getValue())
                .name(warehouse.getName())
                .adminId(warehouse.getAdminId().getValue())
                .street(warehouse.getStreet())
                .city(warehouse.getCity())
                .province(warehouse.getProvince())
                .postalCode(warehouse.getPostalCode())
                .longitude(warehouse.getLongitude())
                .latitude(warehouse.getLatitude())
                .build();
    }

    public User userResponseToUser(UserResponse userResponse) {
        return User.builder()
                .userId(new UserId(userResponse.getUserId()))
                .userName(userResponse.getUserName())
                .firstName(userResponse.getFirstName())
                .lastName(userResponse.getLastName())
                .role(Role.valueOf(userResponse.getRole()))
                .warehouseId(Objects.nonNull(userResponse.getWarehouseId()) ?
                        new WarehouseId(UUID.fromString(userResponse.getWarehouseId())) :
                        null)
                .build();
    }
}
