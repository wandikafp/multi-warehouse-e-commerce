package com.anw.domain.valueobject;

import java.util.UUID;

public class CartItemId extends BaseId<UUID> {
    public CartItemId(UUID value) {
        super(value);
    }
}
