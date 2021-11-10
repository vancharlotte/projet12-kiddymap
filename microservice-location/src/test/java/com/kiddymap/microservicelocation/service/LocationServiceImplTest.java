package com.kiddymap.microservicelocation.service;

import com.kiddymap.microservicelocation.dao.LocationDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LocationServiceImplTest {

    @InjectMocks
    private LocationService locationService;

    @Mock
    private LocationDao locationDaoMock;


    @Test
    void getLocationTest(){
    }

    @Test
    void getAllLocationsTest(){
    }

    @Test
    void deleteLocationTest(){
    }

    @Test
    void saveLocationTest(){
    }

}
