package com.kiddymap.microservicelocation.controller;

import com.kiddymap.microservicelocation.controller.dto.LocationDTO;
import com.kiddymap.microservicelocation.controller.dto.LocationIncompleteDTO;
import com.kiddymap.microservicelocation.exception.EquipmentNotFoundException;
import com.kiddymap.microservicelocation.exception.LocationNotFoundException;
import com.kiddymap.microservicelocation.model.Equipment;
import com.kiddymap.microservicelocation.model.Location;
import com.kiddymap.microservicelocation.service.impl.EquipmentServiceImpl;
import com.kiddymap.microservicelocation.service.impl.LocationServiceImpl;
import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;


@RestController
@Slf4j
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
     * @param location An object LocationIncompleteDTO
     * @return The location object saved
     */
    @PostMapping("/location/protected/add")
    public Location createLocation(@RequestBody LocationIncompleteDTO location) {
        log.info(location.toString());
        List<UUID> equipments = location.getEquipments();
        List<Equipment> newEquipments = new ArrayList<>();

        if(equipments!=null) {

            for (int i = 0; i < equipments.size(); i++) {
                Optional<Equipment> eq = equipmentService.getEquipment(equipments.get(i));
                if (eq.isPresent()) {
                    newEquipments.add(eq.get());
                } else {
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "equipment not Found", new EquipmentNotFoundException("equipment not found"));
                }
            }
        }


        Location newLocation = new Location();
        newLocation.setName(location.getName());
        newLocation.setDescription(location.getDescription());
        newLocation.setLongitude(location.getLongitude());
        newLocation.setLatitude(location.getLatitude());
        newLocation.setEquipments(newEquipments);

        log.info("add new location to the bdd : " + newLocation.getName());

        return locationService.saveLocation(newLocation);
    }


    /**
     * Read - Get one location
     *
     * @param id The id of the location
     * @return A locationDTO object
     */
    @GetMapping("/location/public/get/{id}")
    public LocationDTO getLocation(@PathVariable("id") final UUID id) {
        Optional<Location> location = locationService.getLocation(id);
        if (location.isPresent()) {
            System.out.println(location.get().getName());
            return modelMapper.map(location.get(), LocationDTO.class);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "location not Found", new LocationNotFoundException("location not found"));
        }


    }

    /**
     * Read - Exist
     *
     * @param latitude the latitude of the location
     * @param longitude the longitude of the location
     * @return exist
     */
    @GetMapping("/location/protected/exist/{lat}/{long}")
    public boolean existLocation(@PathVariable("lat") float latitude, @PathVariable("long") float longitude) {
        return locationService.existLocation(latitude, longitude);

    }

    /**
     * Read - Get all locations
     *
     * @return - An Iterable object of location full filled
     */
   /* @GetMapping("/location/public/getAll")
    public Iterable<LocationDTO> getAllLocations() {
        Iterable<Location> locationList = locationService.getAllLocations();
        return modelMapper.map(locationList, new TypeToken<List<LocationDTO>>() {
        }.getType());
    }*/

    /**
     * Read - Get all locations
     * @param minLat minimum latitude
     * @param maxLat maximum latitude
     * @param minLong minimum longitude
     * @param maxLong maximum longitude
     * @return - A List of Location with GeoJSON format
     */
    @GetMapping("/location/public/getAllGeoJson/{minLat}/{maxLat}/{minLong}/{maxLong}")
    public JSONObject getLocationsGeoJsonInBetween(@PathVariable("minLat") float minLat, @PathVariable("maxLat") float maxLat, @PathVariable("minLong") float minLong, @PathVariable("maxLong") float maxLong) {
        JSONObject featureCollection = new JSONObject();
        featureCollection.put("type", "featureCollection");
        JSONArray featureList = new JSONArray();

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

               feature.put("geometry", point);
               feature.put("type", "Feature");
               feature.put("properties", properties);

               featureList.add(feature);


           }

           featureCollection.put("features", featureList);

           return featureCollection;


    }

    /**
     * Update - Update an existing location
     *
     * @param id       - The id of the location to update
     * @param location - A LocationIncompleteDTO object
     * @return location saved
     */
    @PutMapping("/location/protected/update/{id}")
    public Location updateLocation(@PathVariable("id") final UUID id, @RequestBody LocationIncompleteDTO location) {
        Optional<Location> e = locationService.getLocation(id);

        if (e.isPresent()) {
            List<UUID> equipments = location.getEquipments();
            List<Equipment> newEquipments = new ArrayList<>();
            if(equipments!=null) {
                for (int i = 0; i < equipments.size(); i++) {
                    Optional<Equipment> eq = equipmentService.getEquipment(equipments.get(i));
                    if (eq.isPresent()) {
                        newEquipments.add(eq.get());
                    } else {
                        throw new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "equipment not Found", new EquipmentNotFoundException("equipment not found"));

                    }
                }
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
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "location not Found", new LocationNotFoundException("location not found"));
        }
    }

    /**
     * Delete - Delete a location
     *
     * @param id - The id of the location to delete
     */
    @DeleteMapping("/location/protected/delete/{id}")
    public void deleteLocation(@PathVariable("id") final UUID id) {
        Optional<Location> e = locationService.getLocation(id);
        if (e.isPresent()) {
            locationService.deleteLocation(id);
        }
        else{
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "location not Found", new LocationNotFoundException("location not found"));
        }
    }

}
