package com.anw.user.service.dataaccess.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
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
    private String username;
    private String fullName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    private UUID warehouseId;

    @CreationTimestamp
    @Column(name="created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name="updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;
}
