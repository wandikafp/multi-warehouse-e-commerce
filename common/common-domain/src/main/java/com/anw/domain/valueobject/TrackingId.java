package com.anw.domain.valueobject;

import java.util.UUID;

public class TrackingId extends BaseId<UUID>{
    public TrackingId(UUID value) {
        super(value);
    }
}
