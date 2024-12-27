package com.anw.order.service.domain.entity;

import com.anw.domain.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Category extends BaseEntity<UUID> {
    private final String name;
    private final String description;
    private final String icon;

    @Builder
    public Category(UUID categoryId,
                    String name,
                    String description,
                    String icon) {
        super.setId(categoryId);
        this.name = name;
        this.description = description;
        this.icon = icon;
    }
}
