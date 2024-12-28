package com.anw.warehouse.service.domain.dto;

import com.anw.domain.valueobject.UserId;
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
    private UUID id;
    private String name;
    private UUID adminId;
    private String street;
    private String city;
    private String province;
    private String postalCode;
    private double longitude;
    private double latitude;
}
