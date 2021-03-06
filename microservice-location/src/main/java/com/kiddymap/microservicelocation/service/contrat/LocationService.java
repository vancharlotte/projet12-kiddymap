package com.kiddymap.microservicelocation.service.contrat;

import com.kiddymap.microservicelocation.model.Location;

import java.util.Optional;
import java.util.UUID;

public interface LocationService {

    Location saveLocation(Location location);

    Optional<Location> getLocation(UUID id);

    Iterable<Location> getAllLocations();

    void deleteLocation(UUID id);

    Iterable<Location> getAllLocationsInBetween(float minLat, float maxLat, float minLong, float maxLong);

    boolean existLocation(float latitude,float longitude);
}
