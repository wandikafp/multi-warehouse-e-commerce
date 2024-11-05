package com.anw.warehouse.service.domain.dto;

import com.anw.domain.valueobject.Address;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class WarehouseBaseResponse {
    private final UUID id;
    private final UUID adminId;
    @NotNull
    private final Address locationAddress;
}
