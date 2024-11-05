package com.anw.warehouse.service.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WarehouseAddress {
    @NotNull
    private String street;
    @NotNull
    private String city;
    @NotNull
    private String province;
    @NotNull
    private String postalCode;

}
