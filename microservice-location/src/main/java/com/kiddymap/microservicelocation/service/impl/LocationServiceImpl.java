package com.kiddymap.microservicelocation.service.impl;

import com.kiddymap.microservicelocation.dao.LocationDao;
import com.kiddymap.microservicelocation.model.Location;
import com.kiddymap.microservicelocation.service.contrat.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationDao locationDao;

    @Override
    public Location saveLocation(Location location) {
        return locationDao.save(location);
    }

    @Override
    public Optional<Location> getLocation(UUID id) {
        return locationDao.findById(id);
    }

    @Override
    public Iterable<Location> getAllLocations() {
        return locationDao.findAll();
    }

    @Override
    public void deleteLocation(UUID id) {
        locationDao.deleteById(id);
    }
}
