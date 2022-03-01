package com.kiddymap.microservicelocation.controller;

import com.kiddymap.microservicelocation.controller.dto.EquipmentDTO;
import com.kiddymap.microservicelocation.controller.dto.LocationDTO;
import com.kiddymap.microservicelocation.controller.dto.LocationIncompleteDTO;
import com.kiddymap.microservicelocation.model.Equipment;
import com.kiddymap.microservicelocation.model.Location;
import com.kiddymap.microservicelocation.service.impl.EquipmentServiceImpl;
import com.kiddymap.microservicelocation.service.impl.LocationServiceImpl;
import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.apache.commons.lang.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class LocationRestController {

    @Autowired
    LocationServiceImpl locationService;

    @Autowired
    EquipmentServiceImpl equipmentService;

    @Autowired
    ModelMapper modelMapper;


    /**
     * Create - Add a new location
     *
     * @param location An object location
     * @return The location object saved
     */
    @PostMapping("/location/add")
    public Location createLocation(@RequestBody LocationIncompleteDTO location) {

        List<UUID> equipments =  location.getEquipments();
        List<Equipment> newEquipments = new ArrayList<>();

        for( int i=0; i<equipments.size(); i++ ) {
            Optional<Equipment> eq = equipmentService.getEquipment(equipments.get(i));
            if(eq.isPresent()){ newEquipments.add(eq.get());}
        }


        Location newLocation = new Location();
        newLocation.setName(location.getName());
        newLocation.setDescription(location.getDescription());
        newLocation.setLongitude(location.getLongitude());
        newLocation.setLatitude(location.getLatitude());
        newLocation.setEquipments(newEquipments);

        System.out.println("add location");
        return locationService.saveLocation(newLocation);
    }


    /**
     * Read - Get one location
     *
     * @param id The id of the location
     * @return A location object full filled
     */
    @GetMapping("/location/get/{id}")
    public LocationDTO getLocation(@PathVariable("id") final UUID id) {
        Optional<Location> location = locationService.getLocation(id);
        if (location.isPresent()) {
            System.out.println("id");
            return modelMapper.map(location.get(), LocationDTO.class);
        } else {
            System.out.println("null");

            return null;
        }
    }

    /**
     * Read - Exist
     *
     * @param latitude the lat of the location, long the long of the location
     * @return A location object full filled
     */
    @GetMapping("/location/exist/{lat}/{long}")
    public boolean existLocation(@PathVariable("lat") float latitude, @PathVariable("long") float longitude) {
        return locationService.existLocation(latitude, longitude);

    }

    /**
     * Read - Get all locations
     *
     * @return - An Iterable object of location full filled
     */
    @GetMapping("/location/getAll")
    public Iterable<LocationDTO> getAllLocations() {
        Iterable<Location> locationList = locationService.getAllLocations();
        return modelMapper.map(locationList, new TypeToken<List<LocationDTO>>() {
        }.getType());
    }

    @GetMapping("/location/getAllGeoJson/{minLat}/{maxLat}/{minLong}/{maxLong}")
    public JSONObject getLocationsGeoJsonInBetween(@PathVariable("minLat") float minLat, @PathVariable("maxLat") float maxLat, @PathVariable("minLong") float minLong, @PathVariable("maxLong") float maxLong) {
        JSONObject featureCollection = new JSONObject();
        System.out.println("minLat : " + minLat + ", maxLat : " + maxLat);
        featureCollection.put("type", "featureCollection");

        JSONArray featureList = new JSONArray();

        // for (Location obj : locationService.getAllLocations()) {
        for (Location obj : locationService.getAllLocationsInBetween(minLat, maxLat, minLong, maxLong)) {

            JSONObject feature = new JSONObject();

            JSONObject point = new JSONObject();

            List<Float> coordinates = new ArrayList<>();
            coordinates.add(obj.getLongitude());
            coordinates.add(obj.getLatitude());

            point.put("type", "Point");
            point.put("coordinates", coordinates);

            JSONObject properties = new JSONObject();
            properties.put("id", obj.getId());
            properties.put("name", obj.getName());
            properties.put("description", obj.getDescription());

         //   properties.put("equipment", obj.getEquipments());

            feature.put("geometry", point);
            feature.put("type", "Feature");
            feature.put("properties", properties);

            featureList.add(feature);


        }

        featureCollection.put("features", featureList);
       // System.out.println(featureCollection.toString());

        return featureCollection;
    }

    /**
     * Update - Update an existing location
     *
     * @param id       - The id of the location to update
     * @param location - The location object updated
     * @return
     */
    @PutMapping("/location/update/{id}")
    public Location updateLocation(@PathVariable("id") final UUID id, @RequestBody LocationIncompleteDTO location) {


        Optional<Location> e = locationService.getLocation(id);
        if (e.isPresent()) {
            List<UUID> equipments =  location.getEquipments();
            List<Equipment> newEquipments = new ArrayList<>();

            for( int i=0; i<equipments.size(); i++ ) {
                Optional<Equipment> eq = equipmentService.getEquipment(equipments.get(i));
                if(eq.isPresent()){ newEquipments.add(eq.get());}
            }

            Location currentLocation = e.get();
            currentLocation.setLongitude(location.getLongitude());
            currentLocation.setLatitude(location.getLatitude());
            currentLocation.setName(location.getName());
            currentLocation.setDescription(location.getDescription());
            currentLocation.setEquipments(newEquipments);

            locationService.saveLocation(currentLocation);
            return currentLocation;

        } else {
            return null;
        }
    }

    /**
     * Delete - Delete a location
     *
     * @param id - The id of the location to delete
     */
    @DeleteMapping("/location/delete/{id}")
    public void deleteLocation(@PathVariable("id") final UUID id) {
        locationService.deleteLocation(id);
    }

}
