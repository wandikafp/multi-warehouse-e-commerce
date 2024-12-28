package com.anw.warehouse.service.domain.entity;

import com.anw.domain.entity.AggregateRoot;
import com.anw.domain.valueobject.UserId;
import com.anw.domain.valueobject.WarehouseId;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Warehouse extends AggregateRoot<WarehouseId> {
    private final String name;
    private final UserId adminId;
    private final String street;
    private final String city;
    private final String province;
    private final String postalCode;
    private final double longitude;
    private final double latitude;

    private Warehouse(Builder builder) {
        super.setId(builder.warehouseId);
        this.name = builder.name;
        this.adminId = builder.adminId;
        this.street = builder.street;
        this.city = builder.city;
        this.province = builder.province;
        this.postalCode = builder.postalCode;
        this.longitude = builder.longitude;
        this.latitude = builder.latitude;
    }

    public void initializeWarehouse() {
        setId(new WarehouseId(UUID.randomUUID()));
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private WarehouseId warehouseId;
        private String name;
        private UserId adminId;
        private String street;
        private String city;
        private String province;
        private String postalCode;
        private double longitude;
        private double latitude;

        public Builder warehouseId(WarehouseId warehouseId) {
            this.warehouseId = warehouseId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder adminId(UserId adminId) {
            this.adminId = adminId;
            return this;
        }

        public Builder street(String street) {
            this.street = street;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder province(String province) {
            this.province = province;
            return this;
        }

        public Builder postalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder longitude(double longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder latitude(double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Warehouse build() {
            return new Warehouse(this);
        }
    }
}
