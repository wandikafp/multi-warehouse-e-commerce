package com.anw.warehouse.service.dataaccess.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_main")
public class UserEntity {
    @Id
    private UUID id;
    @Column(unique = true)
    private String userName;
    private String firstName;
    private String lastName;
    private String role;
    @Column(nullable = true)
    private UUID warehouseId;
}
