package com.anw.user.service.domain.dto.user;

import lombok.Data;

import java.util.UUID;

@Data
public class UserAddressCommand {
    private UUID id;
    private UUID userId;
    private String street;
    private String city;
    private String province;
    private String postalCode;
    private double longitude;
    private double latitude;
}
