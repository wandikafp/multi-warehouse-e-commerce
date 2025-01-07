package com.anw.user.service.domain.mapper;

import com.anw.domain.valueobject.Address;
import com.anw.domain.valueobject.UserId;
import com.anw.user.service.domain.dto.user.UserAddressCommand;
import com.anw.user.service.domain.dto.user.UserAddressResponse;
import com.anw.user.service.domain.entity.UserAddress;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserAddressDataMapper {
    public UserAddressResponse map(UserAddress userAddress) {
        return UserAddressResponse.builder()
                .id(userAddress.getId())
                .street(userAddress.getAddress().getStreet())
                .city(userAddress.getAddress().getCity())
                .province(userAddress.getAddress().getProvince())
                .postalCode(userAddress.getAddress().getPostalCode())
                .longitude(userAddress.getAddress().getLongitude())
                .latitude(userAddress.getAddress().getLatitude())
                .build();
    }

    public UserAddress map(UserAddressCommand command) {
        return UserAddress.builder()
                .id(command.getId())
                .userId(new UserId(command.getUserId()))
                .address(Address.builder()
                        .street(command.getStreet())
                        .city(command.getCity())
                        .province(command.getProvince())
                        .postalCode(command.getPostalCode())
                        .longitude(command.getLongitude())
                        .latitude(command.getLatitude())
                        .build())
                .build();
    }
}
