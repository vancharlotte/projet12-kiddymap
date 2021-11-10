package com.kiddymap.microservicelocation.controller;

import com.kiddymap.microservicelocation.model.Location;
import com.kiddymap.microservicelocation.service.contrat.LocationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LocationRestControllerTest {

    @InjectMocks
    private LocationRestController locationRestController;

    @Mock
    private LocationService locationServiceMock;

    @BeforeAll
    public static void createListLocations() {
        Location location1 = new Location();
        location1.setName("location1");
        location1.setDescription("description1");

        Location location2 = new Location();
        location2.setName("location2");
        location2.setDescription("description");

    }

    @Test
    void createLocationTest(){
    }

    @Test
    void getLocationTest(){
    }

    @Test
    void getAllLocationsTest(){

    }

    @Test
    void updateLocationTest(){
    }

    @Test
    void deleteLocationTest(){
    }

    @Test
    void saveLocationTest(){
    }


}
