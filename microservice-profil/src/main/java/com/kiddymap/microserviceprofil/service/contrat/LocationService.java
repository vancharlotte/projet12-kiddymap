package com.kiddymap.microserviceprofil.service.contrat;

import com.kiddymap.microserviceprofil.model.Location;

import java.util.Optional;
import java.util.UUID;

public interface LocationService {

    Optional<Location> getLocation(UUID id);


}
