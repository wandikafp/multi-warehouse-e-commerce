package com.anw.domain.valueobject;

import java.util.UUID;

public class Address {
    private final UUID id;
    private final String street;
    private final String city;
    private final String province;
    private final String postalCode;

    public Address(UUID id, String street, String city, String province, String postalCode) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }

    public UUID getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getPostalCode() {
        return postalCode;
    }

}
