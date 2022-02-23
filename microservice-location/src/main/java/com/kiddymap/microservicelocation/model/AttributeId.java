package com.kiddymap.microservicelocation.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class AttributeId implements Serializable {


    private UUID locationId;

    private UUID equipmentId;

    public AttributeId() {
    }

    public AttributeId(UUID locationId, UUID equipmentId) {
        this.locationId = locationId;
        this.equipmentId = equipmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttributeId that = (AttributeId) o;
        return Objects.equals(locationId, that.locationId) && Objects.equals(equipmentId, that.equipmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, equipmentId);
    }
}
