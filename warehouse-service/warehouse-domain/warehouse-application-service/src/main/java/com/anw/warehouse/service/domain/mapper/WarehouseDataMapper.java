package com.anw.warehouse.service.domain.mapper;

import com.anw.domain.valueobject.Role;
import com.anw.domain.valueobject.UserId;
import com.anw.domain.valueobject.WarehouseId;
import com.anw.warehouse.service.domain.dto.WarehouseAddress;
import com.anw.warehouse.service.domain.dto.WarehouseBaseResponse;
import com.anw.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.anw.warehouse.service.domain.dto.create.CreateWarehouseResponse;
import com.anw.warehouse.service.domain.dto.message.UserResponse;
import com.anw.warehouse.service.domain.dto.update.UpdateWarehouseCommand;
import com.anw.warehouse.service.domain.dto.update.UpdateWarehouseResponse;
import com.anw.warehouse.service.domain.entity.User;
import com.anw.warehouse.service.domain.entity.Warehouse;
import com.anw.domain.valueobject.Address;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

import static com.anw.domain.DomainConstants.UTC;

@Component
public class WarehouseDataMapper {
    public WarehouseBaseResponse warehouseToWarehouseBaseResponse(Warehouse warehouse) {
        return WarehouseBaseResponse.builder()
                .id(warehouse.getId().getValue())
                .adminId(warehouse.getAdminId().getValue())
                .locationAddress(warehouse.getLocationAddress())
                .build();
    }
    public Warehouse createWarehouseCommandToWarehouse(CreateWarehouseCommand createWarehouseCommand) {
        return Warehouse.builder()
                .adminId(new UserId(createWarehouseCommand.getAdminId()))
                .locationAddress(warehouseAddressToAddress(createWarehouseCommand.getLocationAddress()))
                .createdAt(ZonedDateTime.now(ZoneId.of(UTC)))
                .build();
    }

    public CreateWarehouseResponse warehouseToCreateWarehouseResponse(Warehouse warehouse) {
        return CreateWarehouseResponse.builder()
                .id(warehouse.getId().getValue())
                .adminId(warehouse.getAdminId().getValue())
                .locationAddress(warehouse.getLocationAddress())
                .build();
    }

    public Warehouse updateWarehouseCommandToWarehouse(UpdateWarehouseCommand updateWarehouseCommand) {
        return Warehouse.builder()
                .warehouseId(new WarehouseId(updateWarehouseCommand.getId()))
                .adminId(new UserId(updateWarehouseCommand.getAdminId()))
                .locationAddress(warehouseAddressToAddress(updateWarehouseCommand.getLocationAddress()))
                .build();
    }

    public UpdateWarehouseResponse warehouseToUpdateWarehouseResponse(Warehouse warehouse) {
        return UpdateWarehouseResponse.builder()
                .id(warehouse.getId().getValue())
                .adminId(warehouse.getAdminId().getValue())
                .locationAddress(warehouse.getLocationAddress())
                .build();
    }

    public Address warehouseAddressToAddress(WarehouseAddress warehouseAddress) {
        return new Address(
                UUID.randomUUID(),
                warehouseAddress.getStreet(),
                warehouseAddress.getCity(),
                warehouseAddress.getProvince(),
                warehouseAddress.getPostalCode()
        );
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
