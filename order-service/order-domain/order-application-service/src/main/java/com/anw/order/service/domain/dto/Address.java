package com.anw.order.service.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
    private String street;
    private String city;
    private String province;
    private String postalCode;
    private double longitude;
    private double latitude;
}
