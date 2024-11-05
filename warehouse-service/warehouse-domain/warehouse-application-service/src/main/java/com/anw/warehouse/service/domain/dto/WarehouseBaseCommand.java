package com.anw.warehouse.service.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class WarehouseBaseCommand {
    private UUID adminId;
    @NotNull
    private WarehouseAddress locationAddress;
}
