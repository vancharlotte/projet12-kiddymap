package com.kiddymap.microservicelocation.controller;

import com.kiddymap.microservicelocation.controller.dto.LocationDTO;
import com.kiddymap.microservicelocation.model.Location;
import com.kiddymap.microservicelocation.service.impl.LocationServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
public class LocationRestController {

    @Autowired
    LocationServiceImpl locationService;

    @Autowired
    ModelMapper modelMapper;


    /**
     * Create - Add a new location
     * @param location An object location
     * @return The location object saved
     */
    @PostMapping("/location")
    public Location createLocation(@RequestBody Location location) {
        return locationService.saveLocation(location);
    }


    /**
     * Read - Get one location
     * @param id The id of the location
     * @return A location object full filled
     */
    @GetMapping("/location/{id}")
    public LocationDTO getLocation(@PathVariable("id") final UUID id) {
        Optional<Location> location = locationService.getLocation(id);
        if(location.isPresent()) {
            return modelMapper.map(location.get(), LocationDTO.class);
        } else {
            return null;
        }
    }

    /**
     * Read - Get all locations
     * @return - An Iterable object of location full filled
     */
    @GetMapping("/locations")
    public Iterable<LocationDTO> getAllLocations() {
        Iterable<Location> locationList = locationService.getAllLocations();
        return  modelMapper.map(locationList, new TypeToken<List<LocationDTO>>() {}.getType());
    }

    /**
     * Update - Update an existing location
     * @param id - The id of the location to update
     * @param location - The location object updated
     * @return
     */
    @PutMapping("/location/{id}")
    public Location updateLocation (@PathVariable("id") final UUID id, @RequestBody Location location) {
        Optional<Location> e = locationService.getLocation(id);
        if (e.isPresent()) {
            Location currentLocation = e.get();

            String name = location.getName();
            if (name != null) {
                currentLocation.setName(name);
            }

            locationService.saveLocation(currentLocation);
            return currentLocation;

        } else {
            return null;
        }
    }

    /**
     * Delete - Delete a location
     * @param id - The id of the location to delete
     */
    @DeleteMapping("/location/{id}")
    public void deleteLocation(@PathVariable("id") final UUID id) {
        locationService.deleteLocation(id);
    }

}
