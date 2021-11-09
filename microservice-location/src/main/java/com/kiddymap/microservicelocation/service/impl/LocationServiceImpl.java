package com.kiddymap.microservicelocation.service.impl;

import com.kiddymap.microservicelocation.dao.LocationDao;
import com.kiddymap.microservicelocation.model.Location;
import com.kiddymap.microservicelocation.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationDao locationDao;

    @Override
    public Location saveLocation(Location location) {
        return locationDao.save(location);
    }

    @Override
    public Optional<Location> getLocation(String id) {
        return locationDao.findById(id);
    }

    @Override
    public Iterable<Location> getLocations() {
        return locationDao.findAll();
    }

    @Override
    public void deleteLocation(String id) {
        locationDao.deleteById(id);
    }
}
