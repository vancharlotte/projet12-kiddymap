package com.kiddymap.microserviceprofil.service.impl;

import com.kiddymap.microserviceprofil.dao.LocationDao;
import com.kiddymap.microserviceprofil.model.Location;
import com.kiddymap.microserviceprofil.service.contrat.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationDao locationDao;

    @Override
    public Optional<Location> getLocation(UUID id) {
        return locationDao.findById(id);
    }
}
