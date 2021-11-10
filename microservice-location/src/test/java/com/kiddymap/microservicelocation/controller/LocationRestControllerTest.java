package com.kiddymap.microservicelocation.controller;

import com.kiddymap.microservicelocation.service.LocationService;
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
