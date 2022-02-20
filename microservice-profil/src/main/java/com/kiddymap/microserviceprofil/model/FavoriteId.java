package com.kiddymap.microserviceprofil.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class FavoriteId implements Serializable {

    private UUID profilId;

    private UUID locationId;

    public FavoriteId() {
    }


    public FavoriteId(UUID profilId, UUID locationId) {
        this.profilId = profilId;
        this.locationId = locationId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteId that = (FavoriteId) o;
        return Objects.equals(profilId, that.profilId) && Objects.equals(locationId, that.locationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profilId, locationId);
    }
}
