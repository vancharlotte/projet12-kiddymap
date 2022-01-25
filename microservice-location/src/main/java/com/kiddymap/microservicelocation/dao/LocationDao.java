package com.kiddymap.microservicelocation.dao;

import com.kiddymap.microservicelocation.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface LocationDao extends JpaRepository<Location, UUID> {

    @Query(
            value = "SELECT l FROM Location l Where latitude Between :minLat And :maxLat And longitude Between :minLong And :maxLong")
    Collection<Location> findAllLocationInBetween(float minLat, float maxLat, float minLong, float maxLong);

}
