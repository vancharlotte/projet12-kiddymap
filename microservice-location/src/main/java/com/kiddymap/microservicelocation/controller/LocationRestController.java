package com.kiddymap.microservicelocation.controller;

import com.kiddymap.microservicelocation.model.Location;
import com.kiddymap.microservicelocation.service.impl.LocationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class LocationRestController {

    @Autowired
    LocationServiceImpl locationService;

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
    public Location getLocation(@PathVariable("id") final String id) {
        Optional<Location> location = locationService.getLocation(id);
        if(location.isPresent()) {
            return location.get();
        } else {
            return null;
        }
    }

    /**
     * Read - Get all locations
     * @return - An Iterable object of location full filled
     */
    @GetMapping("/locations")
    public Iterable<Location> getLocations() {
        return locationService.getLocations();
    }

    /**
     * Update - Update an existing location
     * @param id - The id of the location to update
     * @param location - The location object updated
     * @return
     */
    @PutMapping("/location/{id}")
    public Location updateLocation (@PathVariable("id") final String id, @RequestBody Location location) {
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
    @DeleteMapping("/profil/{id}")
    public void deleteLocation(@PathVariable("id") final String id) {
        locationService.deleteLocation(id);
    }




}
