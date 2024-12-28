package com.anw.warehouse.service.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class WarehouseBaseCommand {
    private String name;
    private UUID adminId;
    private String street;
    private String city;
    private String province;
    private String postalCode;
    private double longitude;
    private double latitude;
}
