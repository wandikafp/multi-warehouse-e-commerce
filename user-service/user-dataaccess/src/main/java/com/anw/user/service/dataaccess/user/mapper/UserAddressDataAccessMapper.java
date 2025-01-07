package com.anw.user.service.dataaccess.user.mapper;

import com.anw.domain.valueobject.Address;
import com.anw.domain.valueobject.UserId;
import com.anw.user.service.dataaccess.user.entity.UserAddressEntity;
import com.anw.user.service.domain.entity.UserAddress;
import org.springframework.stereotype.Component;

@Component
public class UserAddressDataAccessMapper {

    public UserAddressEntity userAddressToUserAddressEntity(UserAddress userAddress) {
        return UserAddressEntity.builder()
                .id(userAddress.getId())
                .userId(userAddress.getUserId().getValue())
                .street(userAddress.getAddress().getStreet())
                .city(userAddress.getAddress().getCity())
                .province(userAddress.getAddress().getProvince())
                .postalCode(userAddress.getAddress().getPostalCode())
                .longitude(userAddress.getAddress().getLongitude())
                .latitude(userAddress.getAddress().getLatitude())
                .build();
    }

    public UserAddress userAddressEntityToUserAddress(UserAddressEntity entity) {
        return UserAddress.builder()
                .id(entity.getId())
                .userId(new UserId(entity.getUserId()))
                .address(Address.builder()
                        .street(entity.getStreet())
                        .city(entity.getCity())
                        .province(entity.getProvince())
                        .postalCode(entity.getPostalCode())
                        .longitude(entity.getLongitude())
                        .latitude(entity.getLatitude())
                        .build())
                .build();
    }
}
