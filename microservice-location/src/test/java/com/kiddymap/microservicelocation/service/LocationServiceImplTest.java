package com.kiddymap.microservicelocation.service;

import com.kiddymap.microservicelocation.dao.LocationDao;
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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class LocationServiceImplTest {

    @InjectMocks
    private LocationServiceImpl locationService;

    @Mock
    private LocationDao locationDaoMock;

    private static Location location;
    private static List<Location> locations;


    @BeforeAll
    public static void createListLocations() {
        location = new Location();
        location.setId(UUID.randomUUID());
        location.setName("location");
        location.setDescription("description");

        locations = new ArrayList<>();
        locations.add(location);

    }


    @Test
    void saveLocationTest(){
        Mockito.when(locationDaoMock.save(location)).thenReturn(location);
        assertEquals(location , locationService.saveLocation(location));
    }

    @Test
    void getLocationTest(){
        Mockito.when(locationDaoMock.findById(location.getId())).thenReturn(Optional.of(location));
        assertEquals(location , locationService.getLocation(location.getId()).get());

    }

    @Test
    void getAllLocationsTest(){
        Mockito.when(locationDaoMock.findAll()).thenReturn(locations);
        assertEquals(locations , locationService.getAllLocations());
    }

    @Test
    void deleteLocationTest(){
        locationService.deleteLocation(location.getId());
        Mockito.verify(locationDaoMock).deleteById(location.getId());
    }

    @Test
    void getAllLocationsInBetween(){
        Mockito.when(locationDaoMock.findAllLocationInBetween(1f,2f,1f,2f)).thenReturn(locations);
        assertEquals(locations, locationService.getAllLocationsInBetween(1f,2f,1f,2f));
    }

    @Test
    void existLocation(){
        Mockito.when(locationDaoMock.existsByLatitudeAndLongitude(1f,1f)).thenReturn(true);
        assertTrue(locationService.existLocation(1f,1f));
    }


}
