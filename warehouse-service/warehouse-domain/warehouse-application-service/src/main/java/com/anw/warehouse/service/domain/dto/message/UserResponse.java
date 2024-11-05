package com.anw.warehouse.service.domain.dto.message;

import com.anw.domain.valueobject.WarehouseId;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class UserResponse {
    private UUID userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String role;
    private String warehouseId;
}
