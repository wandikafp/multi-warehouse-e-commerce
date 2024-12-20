package com.anw.warehouse.service.messaging.mapper;

import com.anw.kafka.order.avro.model.RegisterUserRequestAvroModel;
import com.anw.warehouse.service.domain.dto.message.UserResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class WarehouseMessagingDataMapper {
    public UserResponse registerUserRequestAvroModelToUserResponse(RegisterUserRequestAvroModel registerUserRequestAvroModel) {
        return UserResponse.builder()
                .userId(registerUserRequestAvroModel.getId())
                .userName(registerUserRequestAvroModel.getFullName())
                .firstName(registerUserRequestAvroModel.getFullName())
                .lastName(registerUserRequestAvroModel.getFullName())
                .role(registerUserRequestAvroModel.getRole())
                .warehouseId(Optional.ofNullable(registerUserRequestAvroModel.getWarehouseId())
                        .map(UUID::toString)
                        .orElse(null))
                .build();
    }
}
