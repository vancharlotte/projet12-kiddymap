package com.kiddymap.microservicelocation.controller;

import com.kiddymap.microservicelocation.model.Location;
import com.kiddymap.microservicelocation.service.impl.LocationServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class LocationRestControllerTest {

    @InjectMocks
    private LocationRestController locationRestController;

    @Mock
    private LocationServiceImpl locationServiceMock;

    private static Location location;
    private static List<Location> locations;

    @BeforeAll
    public static void createListLocations() {
        location = new Location();
        location.setName("location1");
        location.setDescription("description1");

        locations = new ArrayList<>();
        locations.add(location);


    }

    @Test
    void createLocationTest(){
        Mockito.when(locationServiceMock.saveLocation(location)).thenReturn(location);
        assertEquals(location, locationRestController.createLocation(location));
    }

    @Test
    void getLocationTest(){
        Mockito.when(locationServiceMock.getLocation(location.getId())).thenReturn(Optional.of(location));
        assertEquals(location, locationRestController.getLocation(location.getId()));
    }

    @Test
    void getAllLocationsTest(){
        Mockito.when(locationServiceMock.getAllLocations()).thenReturn(locations);
        assertEquals(locations,locationRestController.getAllLocations());

    }

    @Test
    void updateLocationTest(){
        Mockito.when(locationServiceMock.getLocation(location.getId())).thenReturn(Optional.of(location));
        Mockito.when(locationServiceMock.saveLocation(location)).thenReturn(location);
        assertEquals(location, locationRestController.updateLocation(location.getId(),location));
    }

    @Test
    void deleteLocationTest(){
        locationRestController.deleteLocation(location.getId());
        Mockito.verify(locationServiceMock).deleteLocation(location.getId());

    }



}
