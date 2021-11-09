package com.kiddymap.microservicelocation.service;

import com.kiddymap.microservicelocation.model.Location;

import java.util.Optional;

public interface LocationService {

    Location saveLocation(Location location);

    Optional<Location> getLocation(String id);

    Iterable<Location> getLocations();

    void deleteLocation(String id);
}
