package com.anw.user.service.domain.dto.user;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserAddressResponse {
    private UUID id;
    private String street;
    private String city;
    private String province;
    private String postalCode;
    private double longitude;
    private double latitude;
}
